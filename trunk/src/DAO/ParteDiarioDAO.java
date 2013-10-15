/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Materiales;
import Modelo.ParteDiario;
import Modelo.Perfiles;
import Modelo.Registro;
import Modelo.RegistroEquipo;
import Utils.FechaUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author matuu
 */
public class ParteDiarioDAO {

    Connection conector;

    public ParteDiarioDAO() {
    }

    public void conectar() {
        try {
            conector = ConectorDB.getConector();

        } catch (Exception ex) {
        }
    }

    public boolean numeroExistsNew(String num) {
        /*retorna true si no debe modificarse*/
        boolean r = false;
        String query = "";
        try {
            query = "SELECT NUMERO FROM partediario WHERE NUMERO LIKE ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setString(1, num);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.print("Error al consultar el número.\n");
        }
        return r;

    }

    public boolean numeroExistsMof(int id, String num) {
        /*retorna true si no debe modificarse*/
        boolean r = true;
        String query = "";
        try {
            query = "SELECT NUMERO FROM partediario WHERE ID = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();    
            if (rs.next()) {
                String codigo = rs.getString("NUMERO");
                //El numero no se modificó
                if(codigo.equals(num)){ 
                    r = false;
                //El número se modificó
                }else{ 
                    r = numeroExistsNew(num);
                }
                
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.print("Error al consultar el número.\n");
            System.out.print(ex.getMessage());
        }
        return r;

    }

    public boolean quitarMateriales(Materiales m) {
        boolean r = false;
        String query = "";
        try {
            query = "delete from materiales where ID = " + m.getId();
            PreparedStatement ps = conector.prepareStatement(query);
            if (ps.executeUpdate() != 0) {
                r = true;
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.print("Error al eliminar el registro de materiales.\n");
        }
        return r;

    }

    public boolean eliminar(ParteDiario pd) {
        //registro registroEquipo array de materiales y parte
        boolean r = false;
        String query = "";
        try {
            conector.setAutoCommit(false);
            query = "delete from registro where ID = ?";
            PreparedStatement registros = conector.prepareStatement(query);
            registros.setInt(1, pd.getIdHorario());
            registros.executeUpdate();
            ResultSet rs = registros.getGeneratedKeys();

            query = "delete from registro_equipo where ID = ?";
            PreparedStatement regEq = conector.prepareStatement(query);
            regEq.setInt(1, pd.getIdEquipo());
            regEq.executeUpdate();
            rs = regEq.getGeneratedKeys();

            query = "delete from materiales where ID_REGISTROEQUIPO = ?";
            PreparedStatement mat = conector.prepareStatement(query);
            mat.setInt(1, pd.getIdEquipo());
            mat.executeUpdate();
            rs = mat.getGeneratedKeys();

            query = "delete from partediario where ID=?";
            PreparedStatement part = conector.prepareStatement(query);
            part.setInt(1, pd.getId());
            part.executeUpdate();
            rs = part.getGeneratedKeys();

            conector.commit();
            r = true;
            rs.close();
            registros.close();
            part.close();
            mat.close();
            regEq.close();
            conector.setAutoCommit(true);

        } catch (SQLException ex) {
            r = false;
            deshacer();
        }

        return r;
    }

    public int guardar(Perfiles p, ParteDiario pd, Registro reg, RegistroEquipo regEq, ArrayList<Materiales> datosTr) {
        int r = -1;
        String query = null;

        try {
            conector.setAutoCommit(false);
            //Si tiene registros
            if(p.obra.isTieneRegistro()){
            // Ingresamos los horarios
            query = "insert into registro (ID, ESPECIAL, HS_SALIDA, HS_LLEGADA, HS_INICIO, "
                    + "HS_FIN, HS_IALMUERZO, HS_FALMUERZO, DIA, FECHA, HS_NORMAL,"
                    + " HS_VIAJE, HS_ALMUERZO, HS_50, HS_100, HS_TOTAL, HS_TAREA, "
                    + "COMIDA, VIANDA, VIANDA_DESA) values "
                    + "(NULL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement registros = conector.prepareStatement(query);
            registros.setBoolean(1, reg.isEspecial());
            registros.setTime(2, reg.getHs_salida());
            registros.setTime(3, reg.getHs_llegada());
            registros.setTime(4, reg.getHs_inicio());
            registros.setTime(5, reg.getHs_fin());
            registros.setTime(6, reg.getHs_ialmuerzo());
            registros.setTime(7, reg.getHs_falmuerzo());
            registros.setString(8, reg.getDia());
            registros.setString(9, reg.getFecha());
            registros.setTime(10, reg.getHs_normal());
            registros.setTime(11, reg.getHs_viaje());
            registros.setTime(12, reg.getHs_almuerzo());
            registros.setTime(13, reg.getHs_50());
            registros.setTime(14, reg.getHs_100());
            registros.setTime(15, reg.getTotal_hs());
            registros.setTime(16, reg.getHs_tarea());
            registros.setInt(17, reg.getComida());
            registros.setInt(18, reg.getVianda());
            registros.setInt(19, reg.getVianda_desa());


            registros.executeUpdate();
            ResultSet generatedKeys = registros.getGeneratedKeys();
            if (generatedKeys.next()) {
                reg.setId(generatedKeys.getInt(1));
            }
            if (reg.getId() == 0) {
                deshacer();
                return -1;
            }
            pd.setIdHorario(reg.getId());
            registros.close();
            generatedKeys.close();
            }
            
            // Si el perfil tiene equipos
            if(p.obra.isTieneEquipo()){
                // ingresamos los datos del equipamiento si corresponde
                // ID EQUIPO INI_HORO FIN_HORO INI_ODO FIN_ODO CANT_COMBUSTIBLE EST_SERVICIO TAREA DATOS_CARGA
                query = "insert into registro_equipo (ID, EQUIPO, INI_HORO, FIN_HORO, "
                        + "INI_ODO, FIN_ODO, CANT_COMBUSTIBLE, IDSERVICIO, TAREA, DATOS_CARGA)"
                        + " values (NULL,?,?,?,?,?,?,?,?,?)";

                PreparedStatement regEquipos = conector.prepareStatement(query);
                if (regEq.getIdEquipo() != 0) {
                    regEquipos.setInt(1, regEq.getIdEquipo());
                    regEquipos.setString(2, regEq.getIniHoro());
                    regEquipos.setString(3, regEq.getFinHoro());
                    regEquipos.setString(4, regEq.getIniOdo());
                    regEquipos.setString(5, regEq.getFinOdo());
                    regEquipos.setString(6, regEq.getCantCombustible());
                    regEquipos.setInt(7, regEq.getEstacionServicioID());
                    regEquipos.setString(8, regEq.getTarea());
                    regEquipos.setBoolean(9, regEq.isDatosCarga());
                    regEquipos.executeUpdate();
                    ResultSet generatedKeys = regEquipos.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        regEq.setId(generatedKeys.getInt(1));
                    }
                    if (regEq.getId() == 0) {
                        deshacer();
                        return -1;
                    }
                    regEquipos.close();
                    generatedKeys.close();
                }

                pd.setIdEquipo(regEq.getId());
            
            
                //ingresamos los datos de transporte si corresponde
                query = " insert into materiales (ID, MATERIAL, CANTIDAD, DISTANCIA, "
                        + "VIAJES, CANTERA_CARGADERO, ID_REGISTROEQUIPO ) "
                        + "values (NULL, ?,?,?,?,?,?)";
                PreparedStatement mat = conector.prepareStatement(query);
                if (regEq.isDatosCarga() || regEq.getIdEquipo() != 1) { //corresponde cargar datos de transporte?

                    mat = conector.prepareStatement(query);
                    for (int i = 0; i < datosTr.size(); i++) {
                        Materiales m = (Materiales) datosTr.get(i);
                        mat.setString(1, m.getMaterial());
                        mat.setString(2, m.getCantidad());
                        mat.setString(3, m.getDistancia());
                        mat.setString(4, m.getViajes());
                        mat.setString(5, m.getCantera_cargadero());
                        mat.setInt(6, regEq.getId());

                        mat.executeUpdate();
                        ResultSet generatedKeys = mat.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            m.setId(generatedKeys.getInt(1));
                        }
                        if (m.getId() == 0) {
                            deshacer();
                            return -1;
                        }
                        mat.close();
                        generatedKeys.close();
                    }
                }
            }
            
            
            //ID NUMERO OPERARIO FUNCION FECHA OBRA HORARIO EQUIPO OBSERVACIONES
            query = "insert into partediario (ID, NUMERO, OPERARIO, FECHA, OBRA, "
                    + "OBSERVACIONES, SITUACION";
            if(p.obra.isTieneRegistro()) query+= ", FUNCION, HORARIO, DESARRAIGO, MULTIFUNCION";
            if(p.obra.isTieneEquipo()) query+= ", EQUIPO";
            query+= ") values (NULL, ?,?,?,?,?,?";
            if(p.obra.isTieneRegistro()) query += ",?,?,?,?";
            if(p.obra.isTieneEquipo()) query+= ",?";
            query+= ")";
            PreparedStatement partes = conector.prepareStatement(query);
            int i = 1;
            partes.setString(i++, pd.getNumero());
            partes.setInt(i++, pd.getIdOperario());
            partes.setDate(i++, new java.sql.Date(pd.getFecha().getTime()));
            partes.setInt(i++, pd.getIdObra());
            partes.setString(i++, pd.getObservaciones());
            partes.setInt(i++, pd.getIdSituacion());
            
            if(p.obra.isTieneRegistro()){
                partes.setInt(i++, pd.getIdFuncion());
                partes.setInt(i++, pd.getIdHorario());
                partes.setBoolean(i++, pd.isDesarraigo());
                partes.setBoolean(i++, pd.isMultifuncion());
            }
            if(p.obra.isTieneEquipo())
                partes.setInt(i++, pd.getIdEquipo());
            partes.executeUpdate();
            ResultSet generatedKeys = partes.getGeneratedKeys();
            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }
            conector.commit();
            generatedKeys.close();
            
            partes.close();
            
            
            conector.setAutoCommit(true);


        } catch (SQLException ex) {
            r = -1;
            deshacer();
        }
        return r;
    }

    public int actualizar(Perfiles perfil, ParteDiario pd, Registro reg, RegistroEquipo regEq, ArrayList<Materiales> datosTr) {
        int r = -1;
        String query = null;

        try {
            conector.setAutoCommit(false);
            if(perfil.obra.isTieneRegistro()){
                // ID ESPECIAL HS_SALIDA HS_LLEGADA HS_INICIO HS_FIN HS_IALMUERZO HS_FALMUERZO
                // DIA FECHA HS NORMAL HS_VIAJE HS_ALMUERZO HS_50 HS_100 HS_TOTAL
                // Ingresamos los horarios
                if (reg.getId() != 0) {
                    query = "update registro set ESPECIAL=?, HS_SALIDA=?, HS_LLEGADA=?, HS_INICIO=?, "
                            + "HS_FIN=?, HS_IALMUERZO=?, HS_FALMUERZO=?, DIA=?, FECHA=?, HS_NORMAL=?,"
                            + " HS_VIAJE=?, HS_ALMUERZO=?, HS_50=?, HS_100=?, HS_TOTAL=?, HS_TAREA=?,"
                            + " COMIDA=?, VIANDA=?, VIANDA_DESA=? where ID =" + reg.getId();
                } else {
                    query = "insert into registro (ID, ESPECIAL, HS_SALIDA, HS_LLEGADA, HS_INICIO, "
                            + "HS_FIN, HS_IALMUERZO, HS_FALMUERZO, DIA, FECHA, HS_NORMAL,"
                            + " HS_VIAJE, HS_ALMUERZO, HS_50, HS_100, HS_TOTAL, HS_TAREA, "
                            + "COMIDA, VIANDA, VIANDA_DESA) values "
                            + "(NULL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                }
                PreparedStatement registros = conector.prepareStatement(query);
                registros.setBoolean(1, reg.isEspecial());
                registros.setTime(2, reg.getHs_salida());
                registros.setTime(3, reg.getHs_llegada());
                registros.setTime(4, reg.getHs_inicio());
                registros.setTime(5, reg.getHs_fin());
                registros.setTime(6, reg.getHs_ialmuerzo());
                registros.setTime(7, reg.getHs_falmuerzo());
                registros.setString(8, reg.getDia());
                registros.setString(9, reg.getFecha());
                registros.setTime(10, reg.getHs_normal());
                registros.setTime(11, reg.getHs_viaje());
                registros.setTime(12, reg.getHs_almuerzo());
                registros.setTime(13, reg.getHs_50());
                registros.setTime(14, reg.getHs_100());
                registros.setTime(15, reg.getTotal_hs());
                registros.setTime(16, reg.getHs_tarea());
                registros.setInt(17, reg.getComida());
                registros.setInt(18, reg.getVianda());
                registros.setInt(19, reg.getVianda_desa());


                registros.executeUpdate();
                ResultSet generatedKeys = registros.getGeneratedKeys();
                if (generatedKeys.next()) {
                    reg.setId(generatedKeys.getInt(1));
                }
                if (reg.getId() == 0) {
                    deshacer();
                    return -1;
                }
                generatedKeys.close();
                registros.close();
                pd.setIdHorario(reg.getId());
            }
            if(perfil.obra.isTieneEquipo()){
                // ingresamos los datos del equipamiento si corresponde
                // ID EQUIPO INI_HORO FIN_HORO INI_ODO FIN_ODO CANT_COMBUSTIBLE EST_SERVICIO TAREA DATOS_CARGA
                if (regEq.getId() != 0) {
                    query = "update registro_equipo set EQUIPO=?, INI_HORO=?, FIN_HORO=?, "
                            + "INI_ODO=?, FIN_ODO=?, CANT_COMBUSTIBLE=?, IDSERVICIO=?, TAREA=?, "
                            + "DATOS_CARGA=? where ID =" + regEq.getId();
                } else {
                    query = "insert into registro_equipo (ID, EQUIPO, INI_HORO, FIN_HORO, "
                            + "INI_ODO, FIN_ODO, CANT_COMBUSTIBLE, IDSERVICIO, TAREA, DATOS_CARGA)"
                            + " values (NULL,?,?,?,?,?,?,?,?,?)";
                }
                PreparedStatement regEquipos = conector.prepareStatement(query);
                if (regEq.getIdEquipo() != 0) {
                    regEquipos.setInt(1, regEq.getIdEquipo());
                    regEquipos.setString(2, regEq.getIniHoro());
                    regEquipos.setString(3, regEq.getFinHoro());
                    regEquipos.setString(4, regEq.getIniOdo());
                    regEquipos.setString(5, regEq.getFinOdo());
                    regEquipos.setString(6, regEq.getCantCombustible());
                    regEquipos.setInt(7, regEq.getEstacionServicioID());
                    regEquipos.setString(8, regEq.getTarea());
                    regEquipos.setBoolean(9, regEq.isDatosCarga());
                    regEquipos.executeUpdate();
                    ResultSet generatedKeys = regEquipos.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        regEq.setId(generatedKeys.getInt(1));
                    }
                    if (regEq.getId() == 0) {
                        deshacer();
                        return -1;
                    }
                    generatedKeys.close();
                    
                }
                regEquipos.close();
                pd.setIdEquipo(regEq.getId());

                if (regEq.isDatosCarga() || regEq.getIdEquipo() != 1) { //corresponde cargar datos de transporte?
                    PreparedStatement mat = conector.prepareStatement(query);
                    for (int i = 0; i < datosTr.size(); i++) {
                        Materiales m = (Materiales) datosTr.get(i);
                        if (m.getId() != 0) {
                            query = "update materiales set MATERIAL=?, CANTIDAD=?, DISTANCIA=?, "
                                    + "VIAJES=?, CANTERA_CARGADERO=?, ID_REGISTROEQUIPO=?"
                                    + " where ID =" + m.getId();
                        } else {
                            query = " insert into materiales (ID, MATERIAL, CANTIDAD, DISTANCIA, "
                                    + "VIAJES, CANTERA_CARGADERO, ID_REGISTROEQUIPO ) "
                                    + "values (NULL, ?,?,?,?,?,?)";
                        }

                        mat = conector.prepareStatement(query);

                        mat.setString(1, m.getMaterial());
                        mat.setString(2, m.getCantidad());
                        mat.setString(3, m.getDistancia());
                        mat.setString(4, m.getViajes());
                        mat.setString(5, m.getCantera_cargadero());
                        mat.setInt(6, regEq.getId());

                        mat.executeUpdate();
                        ResultSet generatedKeys = mat.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            m.setId(generatedKeys.getInt(1));
                        }
                        if (m.getId() == 0) {
                            deshacer();
                            return -1;
                        }
                        generatedKeys.close();
                    }//for
                    mat.close();

                } // if
            }

            query = "update partediario set NUMERO=?, OPERARIO=?, OBRA=?, FECHA=?, OBSERVACIONES=?";
            if(perfil.obra.isTieneRegistro()) query += ", FUNCION=?, HORARIO=?, MULTIFUNCION=?, DESARRAIGO=?";
            else query += ", FUNCION=NULL, HORARIO=NULL, MULTIFUNCION=NULL, DESARRAIGO=NULL";
            if(perfil.obra.isTieneEquipo()) query +=", EQUIPO=?";
            else query += ", EQUIPO=NULL";
            query += " where ID =" + pd.getId();
            PreparedStatement partes = conector.prepareStatement(query);
            int i = 1;
            partes.setString(i++, pd.getNumero());
            partes.setInt(i++, pd.getIdOperario());
            partes.setInt(i++, pd.getIdObra());
            
            partes.setDate(i++, new java.sql.Date(pd.getFecha().getTime()));
            partes.setString(i++, pd.getObservaciones());
            if(perfil.obra.isTieneRegistro()){
                partes.setInt(i++, pd.getIdFuncion());
                partes.setInt(i++, pd.getIdHorario());
                partes.setBoolean(i++, pd.isMultifuncion());
                partes.setBoolean(i++, pd.isDesarraigo());
            }
            if(perfil.obra.isTieneEquipo()){
                partes.setInt(i++, pd.getIdEquipo());
            }
            partes.executeUpdate();
            ResultSet generatedKeys = partes.getGeneratedKeys();
            if (generatedKeys.next()) {
                r = generatedKeys.getInt(1);
            }
            conector.commit();
            generatedKeys.close();         
            partes.close();
            conector.setAutoCommit(true);
            r = pd.getId();

        } catch (SQLException ex) {
            r = -1;
            deshacer();
        }
        return r;
    }

    public ArrayList<Materiales> traerArrayMateriales(int idRegistro) {
        String query = null;
        ArrayList<Materiales> mat = new ArrayList<Materiales>();
        try {
            query = "select * from materiales where ID_REGISTROEQUIPO =" + idRegistro;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Materiales m = new Materiales();
                m.setId(rs.getInt("ID"));
                m.setMaterial(rs.getString("MATERIAL"));
                m.setCantidad(rs.getString("CANTIDAD"));
                m.setDistancia(rs.getString("DISTANCIA"));
                m.setViajes(rs.getString("VIAJES"));
                m.setCantera_cargadero(rs.getString("CANTERA_CARGADERO"));
                m.setIdRegistroEquipo(rs.getInt("ID_REGISTROEQUIPO"));
                mat.add(m);



            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar materiales\n");
        }
        return mat;
    }

    public Registro traerRegistro(int id) {
        String query = null;
        Registro r = new Registro();
        try {
            query = "select * from registro where ID =" + id;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                r.setId(rs.getInt("ID"));
                r.setEspecial(rs.getBoolean("ESPECIAL"));
                r.setHs_salida(rs.getTime("HS_SALIDA"));
                r.setHs_llegada(rs.getTime("HS_LLEGADA"));
                r.setHs_inicio(rs.getTime("HS_INICIO"));
                r.setHs_fin(rs.getTime("HS_FIN"));
                r.setHs_ialmuerzo(rs.getTime("HS_IALMUERZO"));
                r.setHs_falmuerzo(rs.getTime("HS_FALMUERZO"));
                r.setDia(rs.getString("DIA"));
                r.setFecha(rs.getString("FECHA"));
                r.setHs_normal(rs.getTime("HS_NORMAL"));
                r.setHs_viaje(rs.getTime("HS_VIAJE"));
                r.setHs_almuerzo(rs.getTime("HS_ALMUERZO"));
                r.setHs_50(rs.getTime("HS_50"));
                r.setHs_100(rs.getTime("HS_100"));
                r.setTotal_hs(rs.getTime("HS_TOTAL"));
                r.setHs_tarea(rs.getTime("HS_TAREA"));
                r.setComida(rs.getInt("COMIDA"));
                r.setVianda(rs.getInt("VIANDA"));
                r.setVianda_desa(rs.getInt("VIANDA_DESA"));



            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar el horario\n");
        }
        return r;
    }

    public int contarMultifuncion(int id_operario, Date desde, Date hasta) {
        int i = 0;
        try {
            String query = "SELECT COUNT( multifuncion ) AS MULTIFC FROM partediario WHERE multifuncion = 1 "
                    + "and fecha >= ? and fecha <= ? and OPERARIO = ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(desde.getTime()));
            ps.setDate(2, new java.sql.Date(hasta.getTime()));
            ps.setInt(3, id_operario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i = rs.getInt("MULTIFC");
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.print("Falló al calcular la cantidad de multifuncion\n");
        }

        return i;
    }

    public String porcentajeObra(int id_operario, Date desde, Date hasta) {
        int total = 0;
        String re = "";
        try {
            /* select obra, count(obra) from partediario group by obra having count(obra)>1 order by obra asc limit 2
             * 
             */
            String query = "select P.obra AS OBRA, count(P.obra) AS CANT, O.codigo AS COD "
                    + "from partediario P, obras O "
                    + "where P.fecha >= ? and P.fecha <= ? and P.OPERARIO = ? and P.obra = O.id "
                    + "group by P.obra "
                    + "having count(P.obra) > 0 "
                    + "order by CANT desc";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(desde.getTime()));
            ps.setDate(2, new java.sql.Date(hasta.getTime()));
            ps.setInt(3, id_operario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total += rs.getInt("CANT");
            }
            rs.beforeFirst();
            int i = 0;
            //Muestra las dos obras más asistidas
            //while (rs.next() && i < 2) {
            
            //Muestra todas las obras asistidas
            while (rs.next()) {
                float a = rs.getInt("CANT");
                a = a / total * 100;
                re += rs.getString("COD");
                re += "(" + Redondear(a) + "%) ";
                i++;
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.print("Falló al calcular los porcentajes de obras\n");
        }

        return re;
    }

    private float Redondear(float numero) {
        return (float) (Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2));
    }

    public ArrayList<Registro> traerRegistroEntre(int id_operario, Date desde, Date hasta) {
        String query = null;
        ArrayList<Registro> array = new ArrayList<Registro>();
        try {
            query = "SELECT R.* FROM partediario PD LEFT JOIN registro R ON PD.horario = R.ID"
                    + " WHERE PD.operario = ?"
                    + " and PD.horario = R.ID and PD.fecha <= ? and PD.fecha >= ?";
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, id_operario);
            ps.setDate(2, new java.sql.Date(hasta.getTime()));
            ps.setDate(3, new java.sql.Date(desde.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Registro r = new Registro();
                if(rs.getInt("ID")!=0){
                    r.setId(rs.getInt("ID"));
                    r.setEspecial(rs.getBoolean("ESPECIAL"));
                    r.setHs_salida(rs.getTime("HS_SALIDA"));
                    r.setHs_llegada(rs.getTime("HS_LLEGADA"));
                    r.setHs_inicio(rs.getTime("HS_INICIO"));
                    r.setHs_fin(rs.getTime("HS_FIN"));
                    r.setHs_ialmuerzo(rs.getTime("HS_IALMUERZO"));
                    r.setHs_falmuerzo(rs.getTime("HS_FALMUERZO"));
                    r.setDia(rs.getString("DIA"));
                    r.setFecha(rs.getString("FECHA"));
                    r.setHs_normal(rs.getTime("HS_NORMAL"));
                    r.setHs_viaje(rs.getTime("HS_VIAJE"));
                    r.setHs_almuerzo(rs.getTime("HS_ALMUERZO"));
                    r.setHs_50(rs.getTime("HS_50"));
                    r.setHs_100(rs.getTime("HS_100"));
                    r.setTotal_hs(rs.getTime("HS_TOTAL"));
                    r.setHs_tarea(rs.getTime("HS_TAREA"));
                    r.setComida(rs.getInt("COMIDA"));
                    r.setVianda(rs.getInt("VIANDA"));
                    r.setVianda_desa(rs.getInt("VIANDA_DESA"));
                }
                array.add(r);


            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar el horario\n");
        }
        return array;
    }

    public RegistroEquipo traerRegistroEquipo(int id) {
        String query = null;
        RegistroEquipo re = new RegistroEquipo();
        try {
            query = "select * from registro_equipo where ID =" + id;
            PreparedStatement ps = conector.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                re.setId(rs.getInt("ID"));
                re.setIdEquipo(rs.getInt("EQUIPO"));
                re.setIniHoro(rs.getString("INI_HORO"));
                re.setFinHoro(rs.getString("FIN_HORO"));
                re.setIniOdo(rs.getString("INI_ODO"));
                re.setFinOdo(rs.getString("FIN_ODO"));
                re.setCantCombustible(rs.getString("CANT_COMBUSTIBLE"));
                re.setEstacionServicioID(rs.getInt("IDSERVICIO"));
                re.setTarea(rs.getString("TAREA"));
                re.setDatosCarga(rs.getBoolean("DATOS_CARGA"));

            }


            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los registros del equipamiento\n");
        }
        return re;
    }

    void deshacer() {
        try {
            conector.rollback();
        } catch (SQLException e) {
            System.out.println("Error. No hemos podido deshacer." + e.getMessage());
        }
    }

    public ArrayList<ParteDiario> buscar1(int i, String q) {
        //SELECT P.*, O.nombre FROM `partediario` AS P, `operarios` AS O WHERE P.OPERARIO = O.ID and  
        String query = "SELECT distinct P.*, O.NOMBRE FROM partediario AS P, operarios AS O WHERE P.OPERARIO = O.ID and ";
        ArrayList<ParteDiario> partes = new ArrayList<ParteDiario>();
        try {

            switch (i) {
                case 1:
                    query += "P.fecha = '" + q + "'";
                    break;
                case 2:
                    query += "P.operario = " + q;
                    break;
                case 4:
                    query += "P.obra = " + q;
                    break;

            }
            query+= " order by fecha desc";
            PreparedStatement ps = conector.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ParteDiario pd = new ParteDiario();
                pd.setId(rs.getInt("ID"));
                pd.setNumero(rs.getString("NUMERO"));
                pd.setIdOperario(rs.getInt("OPERARIO"));
                pd.setIdFuncion(rs.getInt("FUNCION"));
                pd.setFecha(rs.getDate("FECHA"));
                pd.setIdObra(rs.getInt("OBRA"));
                pd.setIdHorario(rs.getInt("HORARIO"));
                pd.setIdEquipo(rs.getInt("EQUIPO"));
                pd.setObservaciones(rs.getString("OBSERVACIONES"));
                pd.setMultifuncion(rs.getBoolean("MULTIFUNCION"));
                pd.setIdSituacion(rs.getInt("SITUACION"));
                pd.setDesarraigo(rs.getBoolean("DESARRAIGO"));
                pd.setNombreO(rs.getString("NOMBRE"));
                partes.add(pd);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return partes;


    }

    public ArrayList<ParteDiario> buscar2(int i, String q1, String q2) {
        String query = "SELECT distinct P.*, O.NOMBRE FROM partediario AS P, operarios AS O WHERE P.OPERARIO = O.ID and ";
        ArrayList<ParteDiario> partes = new ArrayList<ParteDiario>();
        try {

            switch (i) {
                case 3:
                    query += "P.fecha = '" + q1 + "' and P.operario = " + q2;
                    break;
                case 5:
                    query += "P.fecha = '" + q1 + "' and P.obra = " + q2;
                    break;
                case 6:
                    query += "P.operario = " + q1 + " and P.obra = " + q2;
                    break;

            }
            query+= " order by fecha desc";
            PreparedStatement ps = conector.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ParteDiario pd = new ParteDiario();
                pd.setId(rs.getInt("ID"));
                pd.setNumero(rs.getString("NUMERO"));
                pd.setIdOperario(rs.getInt("OPERARIO"));
                pd.setIdFuncion(rs.getInt("FUNCION"));
                pd.setFecha(rs.getDate("FECHA"));
                pd.setIdObra(rs.getInt("OBRA"));
                pd.setIdHorario(rs.getInt("HORARIO"));
                pd.setIdEquipo(rs.getInt("EQUIPO"));
                pd.setObservaciones(rs.getString("OBSERVACIONES"));
                pd.setMultifuncion(rs.getBoolean("MULTIFUNCION"));
                pd.setIdSituacion(rs.getInt("SITUACION"));
                pd.setDesarraigo(rs.getBoolean("DESARRAIGO"));
                pd.setNombreO(rs.getString("NOMBRE"));
                partes.add(pd);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return partes;


    }

    public ArrayList<ParteDiario> buscar3(int i, String q1, String q2, String q3) {
        String query = "SELECT distinct P.*, O.NOMBRE FROM partediario AS P, operarios AS O WHERE P.OPERARIO = O.ID and ";
        ArrayList<ParteDiario> partes = new ArrayList<ParteDiario>();
        try {

            query += " P.fecha = '" + q1 + "' and P.obra = " + q2 + " and P.operario = " + q3;
            query+= " order by fecha desc";
            PreparedStatement ps = conector.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ParteDiario pd = new ParteDiario();
                pd.setId(rs.getInt("ID"));
                pd.setNumero(rs.getString("NUMERO"));
                pd.setIdOperario(rs.getInt("OPERARIO"));
                pd.setIdFuncion(rs.getInt("FUNCION"));
                pd.setFecha(rs.getDate("FECHA"));
                pd.setIdObra(rs.getInt("OBRA"));
                pd.setIdHorario(rs.getInt("HORARIO"));
                pd.setIdEquipo(rs.getInt("EQUIPO"));
                pd.setObservaciones(rs.getString("OBSERVACIONES"));
                pd.setMultifuncion(rs.getBoolean("MULTIFUNCION"));
                pd.setIdSituacion(rs.getInt("SITUACION"));
                pd.setDesarraigo(rs.getBoolean("DESARRAIGO"));
                pd.setNombreO(rs.getString("NOMBRE"));
                partes.add(pd);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return partes;


    }

    public int guardarParteEspecial(ParteDiario pd) {
        int i = 0;
        String query = "";

        try {
            conector.setAutoCommit(false);
            query = "insert into partediario ( OPERARIO, FECHA,"
                    + " OBSERVACIONES, OBRA, NUMERO) values (?,?,?,?,?)";
            PreparedStatement partes = conector.prepareStatement(query);
            partes.setInt(1, pd.getIdOperario());
            partes.setDate(2, new java.sql.Date(pd.getFecha().getTime()));
            partes.setString(3, pd.getObservaciones());
            partes.setInt(4, pd.getIdObra());
            partes.setString(5, pd.getNumero());
            partes.executeUpdate();
            ResultSet generatedKeys = partes.getGeneratedKeys();
            if (generatedKeys.next()) {
                i = generatedKeys.getInt(1);
            }
            conector.commit();
            generatedKeys.close();

            partes.close();
            conector.setAutoCommit(true);


        } catch (SQLException ex) {
            i = -1;
            deshacer();
        }
        return i;
    }

    public int guardarPartesEspecialHasta(ParteDiario pd, Date hasta) {
        int i = 0;
        String query = "";

        try {
            conector.setAutoCommit(false);
            query = "insert into partediario ( OPERARIO, FECHA,"
                    + " OBSERVACIONES, OBRA, NUMERO) values (?,?,?,?,?)";
            PreparedStatement partes = conector.prepareStatement(query);
            ResultSet generatedKeys = null;
            while (pd.getFecha().compareTo(hasta) <= 0) {

                partes.setInt(1, pd.getIdOperario());
                partes.setDate(2, new java.sql.Date(pd.getFecha().getTime()));
                partes.setString(3, pd.getObservaciones());
                partes.setInt(4, pd.getIdObra());
                partes.setString(5, pd.getNumero());
                partes.executeUpdate();
                generatedKeys = partes.getGeneratedKeys();
                if (generatedKeys.next()) {
                    i = generatedKeys.getInt(1);
                }
                GregorianCalendar gc = new GregorianCalendar();

                gc.setTime(pd.getFecha());
                //System.out.println(gc.getTime());
                gc.add(GregorianCalendar.DATE, 1);
                pd.setFecha(gc.getTime());
                //System.out.println(gc.getTime());
            }

            conector.commit();
            generatedKeys.close();

            partes.close();
            conector.setAutoCommit(true);


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            i = -1;
            deshacer();
        }
        return i;
    }

    public ArrayList<ParteDiario> buscarNumero(String q1) {
        String query = "SELECT distinct P.*, O.NOMBRE FROM partediario AS P, operarios AS O WHERE P.OPERARIO = O.ID and ";
        ArrayList<ParteDiario> partes = new ArrayList<ParteDiario>();
        try {

            query += " P.numero like '%" + q1 + "%'";
            query+= " order by fecha desc";
            PreparedStatement ps = conector.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ParteDiario pd = new ParteDiario();
                pd.setId(rs.getInt("ID"));
                pd.setNumero(rs.getString("NUMERO"));
                pd.setIdOperario(rs.getInt("OPERARIO"));
                pd.setIdFuncion(rs.getInt("FUNCION"));
                pd.setFecha(rs.getDate("FECHA"));
                pd.setIdObra(rs.getInt("OBRA"));
                pd.setIdHorario(rs.getInt("HORARIO"));
                pd.setIdEquipo(rs.getInt("EQUIPO"));
                pd.setObservaciones(rs.getString("OBSERVACIONES"));
                pd.setMultifuncion(rs.getBoolean("MULTIFUNCION"));
                pd.setIdSituacion(rs.getInt("SITUACION"));
                pd.setDesarraigo(rs.getBoolean("DESARRAIGO"));
                pd.setNombreO(rs.getString("NOMBRE"));
                partes.add(pd);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return partes;

    }
    
    public ArrayList<ParteDiario> buscarEntreFechasPorEmpleado(int operarioId, Date fechaDesde, Date fechaHasta) {
        String query = "select * from partediario where operario = ? and "
                + "fecha <= ? and fecha >= ?";
        ArrayList<ParteDiario> partes = new ArrayList<ParteDiario>();
        try {
            PreparedStatement ps = conector.prepareStatement(query);
            ps.setInt(1, operarioId);
            ps.setDate(2, FechaUtil.getFechatoDB(fechaHasta));
            ps.setDate(3, FechaUtil.getFechatoDB(fechaDesde));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ParteDiario pd = new ParteDiario();
                pd.setId(rs.getInt("ID"));
                pd.setNumero(rs.getString("NUMERO"));
                pd.setIdOperario(rs.getInt("OPERARIO"));
                pd.setIdFuncion(rs.getInt("FUNCION"));
                pd.setFecha(rs.getDate("FECHA"));
                pd.setIdObra(rs.getInt("OBRA"));
                pd.setIdHorario(rs.getInt("HORARIO"));
                pd.setIdEquipo(rs.getInt("EQUIPO"));
                pd.setObservaciones(rs.getString("OBSERVACIONES"));
                pd.setMultifuncion(rs.getBoolean("MULTIFUNCION"));
                pd.setIdSituacion(rs.getInt("SITUACION"));
                pd.setDesarraigo(rs.getBoolean("DESARRAIGO"));
                partes.add(pd);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            System.out.print("Falló al cargar los Obras.\n");
        }
        return partes;

    }
}

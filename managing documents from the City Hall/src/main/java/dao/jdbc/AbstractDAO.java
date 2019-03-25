package dao.jdbc;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.InstantiationException;

import connection.ConnectionFactory;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class AbstractDAO<T>{
    private final Class<T> type;

    /**
     * Clasa AbstractDAO are ca varibile instanta un canp care v-a retine tipul clasei parametrizabile
     * tipul clasei parametrizabile este determinat chiar in constructorul acestei clase
     * */
    //Constructori
    public AbstractDAO(){
        type=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     *  CreateQueryField va returna un String ce contine o interogarea pentru un  anumit camp  pe care dorim sa-l cautam.
     *@param field
     *@return string
     */
    private String createQueryField(String field)
    {
        StringBuilder s=new StringBuilder();
        s.append("SELECT ");
        s.append(" * ");
        s.append(" FROM ");
        s.append(type.getSimpleName());
        s.append(" Where "+field+" =?");
        return s.toString();
    }

    /**
     * Aceasta metoda creeaza o nou interogare pentru stergerea unei inregistrari dupa un cmap pe care-l vom preciza
     */
    private String createDeleteQuery(String field)
    {
        StringBuilder s=new StringBuilder();
        s.append("Delete ");
        s.append("From ");
        s.append(type.getSimpleName());
        s.append(" Where "+field+" =?");
        return s.toString();
    }

    //Query pentru interogare
    private String createInsertQuery()
    {
        StringBuilder s=new StringBuilder();
        s.append("Insert Into "+type.getSimpleName()+" (");
        int n=type.getDeclaredFields().length;
        for(int i=1;i<n-1;i++) {
            s.append(type.getDeclaredFields()[i].getName());
            s.append(",");
        }
        s.append(type.getDeclaredFields()[n-1].getName()+")");
        s.append(" Values (");
        for(int i=1;i<n-1;i++)
            s.append("?,");
        s.append("?)");
        return s.toString();
    }

    //Query pentru Update
    private String createUpdateQuery()
    {
        StringBuilder s=new StringBuilder();
        s.append("Update "+type.getSimpleName());
        s.append(" Set ");
        int n=type.getDeclaredFields().length;
        for(int i=1;i<n-1;i++)
            s.append(type.getDeclaredFields()[i].getName()+"=?, ");
        s.append(type.getDeclaredFields()[n-1].getName()+"=?");
        s.append(" Where " +type.getDeclaredFields()[0].getName()+"=?");
        return s.toString();
    }

    /**
     * Metoda getList va returna lista de obiecete de tip T obtinuta in urma unei interogari a bazei de date
     * Aceasta metoda este implementata folosind tehnica reflexion
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     */
    public T findById(int id)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Field field=type.getDeclaredFields()[0];
        String query = createQueryField(field.getName());
        try {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();

            return getList(resultSet).get(0);
        }catch(SQLException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }
        return null;
    }

    /**
     * Returneaza lista de obiecte dint-un tabel, rezultatele tabelului se afla intr-un obiecte de tipul REsultSet
     * @param resultSet descr
     * @return List<T>
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private List<T> getList(ResultSet resultSet) throws IllegalArgumentException, InvocationTargetException
    {	List<T> list=new ArrayList<T>();

        try {
            while(resultSet.next())
            {
                T instance = type.newInstance();
                for(Field field:type.getDeclaredFields())
                {

                    Object value=resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),type);
                    Method method=propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }

        }catch(InstantiationException e)
        {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        }catch(SQLException sqlException) {
            System.out.println(sqlException.getMessage().toString());
            sqlException.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        }catch (IntrospectionException e) {
            System.out.println(e.getMessage().toString());
            e.printStackTrace();
        }
        return list;
    }

    ///Metoda de stergere dintr-o lista dupa un id
    public boolean deleteById(int id)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Field field=type.getDeclaredFields()[0];
        String query=createDeleteQuery(field.getName());
        System.out.println(query);
        try {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();

            return true;

        }catch(SQLException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }

        return false;
    }

    //Metoda de inserare a unei noi valori
    public void persist(T t)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query=createInsertQuery();
        try {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            int n=type.getDeclaredFields().length;
            System.out.println(query);
            for(int j=1;j<n;j++)
            {
                Field field=type.getDeclaredFields()[j];
                field.setAccessible(true);
                statement.setObject(j, field.get(t));
            }
            System.out.println("DADADADADADADADADADA");
            try {
                statement.executeUpdate();
            }catch(MySQLIntegrityConstraintViolationException e)
            {
                System.out.println("User already exist");
            }

        }catch(SQLException e) {
            e.printStackTrace();

        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    //Facem Update la un element
    public boolean update(T t,int id)
    {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query=createUpdateQuery();
        try{
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);

            int n=type.getDeclaredFields().length;
            for(int i=1;i<n;i++)
            {
                Field field=type.getDeclaredFields()[i];
                field.setAccessible(true);
                statement.setObject(i, field.get(t));
            }
            statement.setObject(n,id);
            statement.executeUpdate();
            return true;
        }catch(SQLException e) {
            e.printStackTrace();

        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }

    public  List<T> findAll()
    {
        //Creeam conexiunea la baza de date
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet =null;
        String query ="SELECT * FROM "+type.getSimpleName();

        //Verificam conexiunea la baza de date si interogam tabela
        try{
            connection=ConnectionFactory.getConnection();
            preparedStatement =connection.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            System.out.println(query);
            System.out.println(resultSet.toString());
            return getList(resultSet);

        }catch(SQLException e){
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally{
            ConnectionFactory.close(connection);
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
        }
        return null;
    }



}
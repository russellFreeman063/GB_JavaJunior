package ru.geekbrains.junior.homework2.task2;

import java.lang.reflect.Field;
import java.util.UUID;

public class QueryBuilder {


    public String buildInsertQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        // insert into table_name (column1, column2, column3) values ('value1', 'value2', 'value3')

        if (clazz.isAnnotationPresent(Table.class)) {

            StringBuilder query = new StringBuilder("INSERT INTO ");
            Table tableAnnotation = clazz.getAnnotation(Table.class);

            query
                    .append(tableAnnotation.name())
                    .append(" (");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    query.append(columnAnnotation.name()).append(", ");
                }
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }

            query.append(") VALUES (");

            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    query.append("'").append(field.get(obj)).append("', ");
                }
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }

            query.append(")");

            return query.toString();

        }
        else{
            return "";
        }
    }

    public String buildSelectQuery(Class<?> clazz, UUID primaryKey){
        if (clazz.isAnnotationPresent(Table.class)) {
            // select * from table_name where iddddd = 'primaryKey'
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            StringBuilder query = new StringBuilder("SELECT * FROM ");
            query.append(tableAnnotation.name())
                    .append(" WHERE ");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                        query.append(columnAnnotation.name()).append(" = '").append(primaryKey).append("'");
                        break;
                    }
                }
            }

            return query.toString();

        }
        else {
            return "";
        }
    }

    public String buildUpdateQuery(Object obj) throws IllegalAccessException {

        // update table_name set column1 = 'value1', column2 = 'value2' where id = 'primaryKey'
        Class<?> clazz = obj.getClass();
        if (clazz.isAnnotationPresent(Table.class)) {
            StringBuilder query = new StringBuilder("UPDATE ");

            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" SET ");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()){
                        continue;
                    }
                    query.append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("', ");
                }
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }

            query.append(" WHERE ");
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                        query.append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("'");
                        break;
                    }
                }
            }

            return query.toString();

        }
        else {
            return "";
        }

    }

    /**
     * Домашнее задание
     */
    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey) {
        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            StringBuilder query = new StringBuilder("DELETE FROM ");
            query.append(tableAnnotation.name())
                    .append(" WHERE ");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                        query.append(columnAnnotation.name()).append(" = '").append(primaryKey).append("'");
                        break;
                    }
                }
            }
            return query.toString();
        }
        else {
            return "";
        }
    }

}

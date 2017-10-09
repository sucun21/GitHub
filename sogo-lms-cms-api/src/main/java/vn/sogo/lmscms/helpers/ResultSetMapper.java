package vn.sogo.lmscms.helpers;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;

public class ResultSetMapper<T> {
	// This method is already implemented in package
	// but as far as I know it accepts only public class attributes
	private void setProperty(Object clazz, String fieldName, Object columnValue) {
		try {
			// get all fields of the class (including public/protected/private)
			Field field = clazz.getClass().getDeclaredField(fieldName);
			// this is necessary in case the field visibility is set at private
			field.setAccessible(true);
			field.set(clazz, columnValue);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public List<T> mapRersultSetToListObject(ResultSet rs, Class clazz) {
//		List<T> outputList = null;
//		try {
//			// make sure resultset is not null
//			if (rs != null) {
//
//				// check if Class clazz has the 'Entity' annotation
//				if (clazz.isAnnotationPresent(Entity.class)) {
//
//					// get the resultset metadata
//					ResultSetMetaData rsmd = rs.getMetaData();
//
//					// get all the attributes of Class clazz
//					Field[] fields = clazz.getDeclaredFields();
//
//					while (rs.next()) {
//						T bean = (T) clazz.newInstance();
//						for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
//							// get the SQL column name
//							String columnName = rsmd.getColumnName(_iterator + 1);
//
//							// get the value of the SQL column
//							Object columnValue = rs.getObject(_iterator + 1);
//
//							// iterating over clazz attributes to check
//							// if any attribute has 'Column' annotation with
//							// matching 'name' value
//							for (Field field : fields) {
//								if (field.isAnnotationPresent(Column.class)) {
//									Column column = field.getAnnotation(Column.class);
//									if (column.name().equalsIgnoreCase(columnName) && columnValue != null) {
//										this.setProperty(bean, field.getName(), columnValue);
//										break;
//									}
//								}
//							} // EndOf for(Field field : fields)
//						} // EndOf for(_iterator...)
//						if (outputList == null) {
//							outputList = new ArrayList<T>();
//						}
//						outputList.add(bean);
//					} // EndOf while(rs.next())
//				} else {
//					// throw some error that Class clazz
//					// does not have @Entity annotation
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return outputList;
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> mapRersultSetToListObject(ResultSet rs, Class clazz) {
		List<T> outputList = null;
		try {
			// make sure resultset is not null
			if (rs != null) {

				// get the resultset metadata
				ResultSetMetaData rsmd = rs.getMetaData();

				// get all the attributes of Class clazz
				List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

				while (rs.next()) {
					T bean = (T) clazz.newInstance();
					
					for (int i = 0; i < rsmd.getColumnCount(); i++) {

						// String columnName = rsmd.getColumnName(i + 1);
						String columnLabel = rsmd.getColumnLabel(i + 1);
						Object columnValue = rs.getObject(i + 1);

						Field field = fields.stream().filter(x -> x.getName().equals(columnLabel)).findFirst()
								.orElse(null);
						if (field != null) {
							this.setProperty(bean, field.getName(), columnValue);
						}
					}
					
					if (outputList == null) {
						outputList = new ArrayList<T>();
					}
					outputList.add(bean);
				} // EndOf while(rs.next())
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outputList;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T mapRersultSetToObject(ResultSet rs, Class clazz) {
		T output = null;
		try { 
			// make sure resultset is not null
			if (rs != null) {
				// get the resultset metadata
				ResultSetMetaData rsmd = rs.getMetaData();

				// get all the attributes of Class clazz
				List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
				while (rs.next()) {
					
					if(output == null)
					{
						T bean = (T) clazz.newInstance();
						for (int i = 0; i < rsmd.getColumnCount(); i++) {
		
							// String columnName = rsmd.getColumnName(i + 1);
							String columnLabel = rsmd.getColumnLabel(i + 1);
							Object columnValue = rs.getObject(i + 1);
		
							Field field = fields.stream().filter(x -> x.getName().equals(columnLabel)).findFirst()
									.orElse(null);
							if (field != null) {
								this.setProperty(bean, field.getName(), columnValue);
							}
						}
						output = bean;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// public T mapRersultSetToObject(ResultSet rs, Class clazz) {
	// T output = null;
	// try {
	// // make sure resultset is not null
	// if (rs != null) {
	//
	// // check if Class clazz has the 'Entity' annotation
	// if (clazz.isAnnotationPresent(Entity.class)) {
	//
	// // get the resultset metadata
	// ResultSetMetaData rsmd = rs.getMetaData();
	//
	// // get all the attributes of Class clazz
	// List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
	//
	// while (rs.next()) {
	// T bean = (T) clazz.newInstance();
	// for (int i = 0; i < rsmd.getColumnCount(); i++) {
	//
	//// String columnName = rsmd.getColumnName(i + 1);
	// String columnLabel = rsmd.getColumnLabel(i + 1);
	// Object columnValue = rs.getObject(i + 1);
	//
	// Field field = fields.stream()
	// .filter(x -> x.getName().equals(columnLabel))
	// .findFirst()
	// .orElse(null);
	// if(field != null){
	// this.setProperty(bean, field.getName(), columnValue);
	// }
	// }
	// output = bean;
	// } // EndOf while(rs.next())
	// } else {
	// // throw some error that Class clazz
	// // does not have @Entity annotation
	// }
	// } else {
	// // ResultSet is empty
	// return null;
	// }
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (InstantiationException e) {
	// e.printStackTrace();
	// }
	//
	// return output;
	// }
}

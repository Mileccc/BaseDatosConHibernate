package com.academia.controlador;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import com.academia.modelo.Alumno;

public class Acceso {

    // Variable para almacenar la fábrica de sesiones de Hibernate.
    public SessionFactory sessionFactory;

    /**
     * Método para conectar con la base de datos utilizando Hibernate.
     */
    public void conectar() {
        // Construir el registro estándar de servicios de Hibernate.
        final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure().build();

        try {
            // Construir la fábrica de sesiones a partir del registro.
            this.sessionFactory = new MetadataSources(registro).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // En caso de error, destruir el registro y mostrar la traza de la excepción.
            StandardServiceRegistryBuilder.destroy(registro);
            e.printStackTrace();
        }
    }

    /**
     * Método para cerrar la conexión con la base de datos.
     */
    public void desconectar() {
        if (this.sessionFactory != null) {
            this.sessionFactory.close();
        }
    }

    /**
     * Método para añadir un nuevo alumno a la base de datos.
     * @param alumno El alumno a añadir.
     */
    public void añadirAlumno(Alumno alumno) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(alumno); // Guardar el alumno en la base de datos.
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // En caso de error, deshacer la transacción.
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Método para leer un alumno de la base de datos por su ID.
     * @param alumnoId El ID del alumno a leer.
     * @return El alumno encontrado o null si no existe.
     */
    public Alumno leerAlumno(int alumnoId) {
        Session session = sessionFactory.openSession();
        Alumno alumno = null;
        try {
            alumno = session.get(Alumno.class, alumnoId); // Obtener el alumno por su ID.
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return alumno;
    }

    /**
     * Método para borrar un alumno de la base de datos por su ID.
     * @param alumnoId El ID del alumno a borrar.
     */
    public void borrarAlumno(int alumnoId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, alumnoId);
            if (alumno != null) {
                session.delete(alumno); // Eliminar el alumno.
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Método para actualizar los datos de un alumno en la base de datos.
     * @param alumno El alumno con los datos actualizados.
     */
    public void actualizarAlumno(Alumno alumno) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(alumno); // Actualizar el alumno.
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Método para mostrar todos los alumnos de la base de datos.
     */
    public void mostrarAlumnos() {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM Alumno";
            Query query = session.createQuery(hql);
            List<Alumno> alumnos = query.list();
            for (Alumno alumno : alumnos) {
                System.out.println(alumno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Método para contar el número total de alumnos en la base de datos.
     */
    public void cantidadAlumnos() {
        Session session = sessionFactory.openSession();
        try {
            String hql = "SELECT COUNT(*) FROM Alumno";
            Query query = session.createQuery(hql);
            Long count = (Long) query.uniqueResult();
            System.out.println("Total de alumnos: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}


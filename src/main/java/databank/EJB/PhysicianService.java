package databank.EJB;

import databank.model.PhysicianPojo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class PhysicianService {

    @PersistenceContext(name = "PU_DataBank")
    private EntityManager em;

    /*
     * Default constructor
     */
    public PhysicianService() {}

    /**
     * The method that is used to find all the physicians from the table.
     * @return all physicians
     */
    public List<PhysicianPojo> findAllPhysicians(){
        //Use the named JPQL query from the PhysicianPojo class to grab all the students
        TypedQuery<PhysicianPojo> allPhysiciansQuery = em.createNamedQuery(PhysicianPojo.PHYSICIAN_FIND_ALL, PhysicianPojo.class);
        //Execute the query and return the result/s.
        System.out.println(allPhysiciansQuery.getResultList()); // just to print to the console that data of the physician record fetched or not?
        return allPhysiciansQuery.getResultList();
    }

    /**
     * The method that is used to persist(store to database) the physician(creating a physician)
     * @param physician a record
     * @return a physician
     */
    @Transactional
    public PhysicianPojo persistPhysician(PhysicianPojo physician){
        em.persist(physician);
        return physician;
    }


    /**
     * Find the particular physician by primary key using EntityManager find() method.
     * @param pKey primary key of the physician record
     * @return find the physician with the primary key
     */
    public PhysicianPojo findPhysicianByPMKey(int pKey){
        return em.find(PhysicianPojo.class, pKey);
    }

    /**
     * The method used to update the current physician using the merge() method of EntityManager.
     * @param physicianWithUpdates updated version of the physician.
     */
    @Transactional
    public void mergePhysician(PhysicianPojo physicianWithUpdates){
        PhysicianPojo PhysicianToBeUpdated = findPhysicianByPMKey(physicianWithUpdates.getId());
        if(PhysicianToBeUpdated != null){
            em.merge(physicianWithUpdates);
        }
    }


    /**
     * The method that is used to remove the physician record from the table using Remove() and refresh() methods.
     * @param physicianId physician id
     */
    public void removePhysician(int physicianId){
        PhysicianPojo physician = findPhysicianByPMKey(physicianId);
        if(physician != null){
            em.refresh(physician);
            em.remove(physician);
        }
    }

}

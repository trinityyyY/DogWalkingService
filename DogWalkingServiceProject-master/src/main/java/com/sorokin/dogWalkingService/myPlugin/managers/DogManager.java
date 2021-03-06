package com.sorokin.dogWalkingService.myPlugin.managers;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.sorokin.dogWalkingService.myPlugin.entities.IDog;
import com.sorokin.dogWalkingService.myPlugin.models.Dog;
import net.java.ao.Query;

public class DogManager {
    private final ActiveObjects ao;

    private DogManager(ActiveObjects ao) {
        this.ao = ao;
    }

    public static DogManager create (ActiveObjects ao) {
        return new DogManager(ao);
    }

    public void save(Dog model) throws Exception{
        try {
            IDog entity = ao.create(IDog.class);
            model.toEntity(entity);
            entity.save();

//            return true;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
//        return false;
    }
    
    public void deleteAllByOwnerId (String ownerId) throws Exception{

        for (Dog dogs : getByOwnerId(ownerId)) {
            deleteByUniqueId(dogs.getUniqueId());
        }

    } 

    public void deleteByUniqueId(String uniqueId) throws Exception{
        try {
//            Query query = Query.select().where("UNIQUE_ID = '" + uniqueId + "'");
            Query query = Query.select().where("UNIQUE_ID = ?",uniqueId);
            IDog[] entities = ao.find(IDog.class, query);

            if (entities != null && entities.length > 0) {
                ao.delete(entities);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }

    public Dog[] getByOwnerId(String ownerId){
//        try{
//            Query query = Query.select().where("OWNER_ID = '" + ownerId + "'");
            Query query = Query.select().where("OWNER_ID = ?",ownerId);
            IDog[] entities = ao.find(IDog.class,query);

            if(entities != null && entities.length>0){
                Dog [] dogs = new Dog[entities.length];

                for (int i = 0; i < entities.length; i++) {
                    dogs[i] = Dog.fromEntity(entities[i]);
                }
                return dogs;
            } else {
                return null;
            }
//        }catch (Exception ex){
//            String exs = ex.getMessage();
//        }

    }

    public Dog getByUniqueId(String uniqueId) {
//        try {
//            Query query = Query.select().where("UNIQUE_ID = '" + uniqueId + "'");
            Query query = Query.select().where("UNIQUE_ID = ?",uniqueId);
            IDog[] entities = ao.find(IDog.class, query);

            if (entities != null && entities.length > 0) {
                return Dog.fromEntity(entities[0]);
            } else {
                return null;
            }
//        } catch (Exception ex) {
//            String exs = ex.getMessage();
//        }

    }


    public Dog getByIssueId (String issueId) {

//        try {
//            Query query = Query.select().where("ISSUE_ID = '" + issueId + "'");
            Query query = Query.select().where("ISSUE_ID = ?",issueId);
            IDog[] entities = ao.find(IDog.class, query);
            if (entities != null && entities.length > 0) {
                return Dog.fromEntity(entities[0]);
            } else {
                return null;
            }
//        } catch (Exception ex) {
//            String exs = ex.getMessage();
//        }


    }


    public Dog[] getAll() {
//        try {
            Query query = Query.select();
            IDog[] entities = ao.find(IDog.class, query);

            if (entities != null && entities.length > 0) {
                Dog[] dogs = new Dog[entities.length];

                for (int i = 0; i < entities.length; i++) {

                    dogs[i] = Dog.fromEntity(entities[i]);
                }
                return dogs;
            } else {
                return null;
            }

//        } catch (Exception ex) {
//            String exs = ex.getMessage();
//        }

    }

    public IDog getEntityByUniqueId(String uniqueId) {
//        try {
//            Query query = Query.select().where("UNIQUE_ID = '" + uniqueId + "'");
            Query query = Query.select().where("UNIQUE_ID = ?",uniqueId);
            IDog[] entities = ao.find(IDog.class, query);

            if (entities != null && entities.length > 0) {
                return entities[0];
            } else {
                return null;
            }
//        } catch (Exception ex) {
//            String exs = ex.getMessage();
//        }

    }

    public void fullUpdate(Dog model){
//        try {
            if (model != null) {
                IDog entity = getEntityByUniqueId(model.getUniqueId());
                Dog dog = getByUniqueId(model.getUniqueId());
                model.setIssueId(dog.getIssueId());

                if (entity != null) {
                    model.toEntity(entity);
                    entity.save();
                }
            }
//        } catch (Exception ex) {
//            String exs = ex.getMessage();
//        }
    }

    public void update(Dog model) {
//        try {
            if (model != null) {
                IDog entity = getEntityByUniqueId(model.getUniqueId());

                if (entity != null) {
                    Dog dog = Dog.fromEntity(entity);

                    dog.setDogStatus(model.getDogStatus());

                    dog.toEntity(entity);
                    entity.save();
//                    return true;
                }
            }
//        } catch (Exception ex) {
//            String exs = ex.getMessage();
//        }
//        return false;
    }
}

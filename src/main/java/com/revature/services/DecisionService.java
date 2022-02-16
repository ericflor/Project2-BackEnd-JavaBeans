package com.revature.services;

<<<<<<< HEAD

=======
import com.revature.models.Decisions;
import com.revature.models.Group;
>>>>>>> 4c68732e7d88bf02d4f34bc24bcc951a9e5b583f
import com.revature.repos.DecisionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 4c68732e7d88bf02d4f34bc24bcc951a9e5b583f
@Service
public class DecisionService {

    private DecisionDao decisionDao;

    @Autowired
<<<<<<< HEAD
    public DecisionService(DecisionDao decisionDao){
        this.decisionDao = decisionDao;
    }

    public List<>
=======
    public DecisionService(DecisionDao decisionDao) {
        this.decisionDao = decisionDao;
    }

    //we will add movies into Decisions table
    public boolean addMovies(Decisions decisions){
        try {
            decisionDao.save(decisions);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //after voting we will delete the movies from DB
    public void deleteMovies(int id){
        decisionDao.deleteById(id);
    }
>>>>>>> 4c68732e7d88bf02d4f34bc24bcc951a9e5b583f

}

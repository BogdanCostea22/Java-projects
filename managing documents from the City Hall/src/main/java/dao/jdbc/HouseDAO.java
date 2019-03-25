package dao.jdbc;

import dao.DAO;
import model.House;

import java.util.List;


public class HouseDAO extends AbstractDAO<House> implements DAO<House, String> {

        //Override update function
        public void update(House document){
            super.update(document, Integer.parseInt(document.getHouseId()));
        }

    public House findById(String s) {
        List<House> list = super.findAll();

        for(House house : list)
            if(house.getHouseId().equals(s))
                return house;

        return null;
    }

    //Override delete function
        public  void delete(House document){
            super.deleteById(Integer.parseInt(document.getHouseId()));
        }

    public void deleteAll() {

    }
}


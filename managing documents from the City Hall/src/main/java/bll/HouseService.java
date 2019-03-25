package bll;

import dao.DAO;
import factory.AbstractFactory;
import model.House;

import java.util.List;

public class HouseService {
    private static DAO houseDao;

    public HouseService(AbstractFactory abstractFactory) {
        houseDao = abstractFactory.getHouseDao();
    }

    public void persist(House entity) {
        houseDao.persist(entity);
    }

    public void update(House entity) {
        houseDao.update(entity);
    }

    public House findById(String id) {
        House house = (House)houseDao.findById(id);
        return house;
    }

    public void delete(String id) {
        House house = (House)houseDao.findById(id);
        houseDao.delete(house);
    }

    public List<House> findAll() {
        List<House> houses = houseDao.findAll();
        return houses;

    }

    public void deleteAll() {
        houseDao.deleteAll();
    }

    public DAO houseDao() {
        return houseDao;
    }

    //Return last house id
    public String getLastId()
    {
        List<House> houses = houseDao.findAll();

        int max = 0;
        for(House house: houses)
        {
            if(house.getHouseId().compareTo(Integer.toString(max))>0)
                max = Integer.parseInt(house.getHouseId());
        }
        return Integer.toString(max+1);
    }
}
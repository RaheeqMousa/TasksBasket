package DAL;

import java.util.ArrayList;

public class BasketDaMockup {
    private static ArrayList<BasketItem> items;
    public BasketDaMockup(){
        if(items==null){
            items=new ArrayList<>();
            items.add(new BasketItem(2, 8, "Dairy", "Milk"));
            items.add(new BasketItem(2, 18, "Dairy", "Cheese"));
            items.add(new BasketItem(6, 28, "Meat", "Chicken drums"));
            items.add(new BasketItem(6, 36, "Meat", "Steak"));
            items.add(new BasketItem(1, 14, "Bakery", "Bread"));
        }
    }

    public boolean addItem(BasketItem item){
        boolean success= items.add(item);
        if(success)
            return true;
        return false;
    }

    public ArrayList<BasketItem> getAllItems(){
        return items;
    }

    public BasketItem findById(int id){
        for(int i=0;i< items.size();i++){
            if(items.get(i).getId() == id)
                return items.get(i);
        }
        return null;
    }

}

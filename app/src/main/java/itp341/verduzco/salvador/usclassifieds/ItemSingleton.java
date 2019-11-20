package itp341.verduzco.salvador.usclassifieds;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

//TODO complete
public class ItemSingleton {

    //TODO instance variables
    private static ItemSingleton singleton;
    private Context context;
    private List<Item> items;

    //TODO private singleton constructor
    private ItemSingleton(Context context){
        this.context = context;
        items = new ArrayList<>();
    }

    //TODO Singleton get method
    public static ItemSingleton get(Context context){
        if (singleton == null){
            singleton = new ItemSingleton(context);
        }

        return singleton;
    }

    public void SetList(List<Item> newList){
        items = newList;
    }

    public List<Item> getItems(){
        return items;
    }

    public Item getItem(int index){
        return items.get(index);
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(int index){
        items.remove(index);
    }

    public void updateItem(int index, Item item){
        items.set(index, item);
    }


    public void parse(String category){
        for(Item i : items){
            if(!i.getDescription().equalsIgnoreCase(category)){
                items.remove(i);
            }
        }
    }


}
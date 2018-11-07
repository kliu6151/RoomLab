package Items;

public class Items extends Object {
    String name;
    public Items(String name)
    {
        this.name = name;
    }
    public void changeName(String newName)
    {
        name = newName;
    }
    public String getName()
    {
        return name;
    }
}


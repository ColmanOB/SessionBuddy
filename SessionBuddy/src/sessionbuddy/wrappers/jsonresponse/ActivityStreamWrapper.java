package sessionbuddy.wrappers.jsonresponse;

public class ActivityStreamWrapper
{
    public Item[] items;

    public class Item
    {
        public String published;
        public String title;
        public Actor actor;
        public String verb;
        public Object object;
        public Target target;
    }

    public class Actor
    {
        public String url;
        public String objectType;
        public String id;
        public String displayName;
    }

    public class Object
    {
        public String url;
        public String objectType;
        public String id;
        public String displayName;
    }

    public class Target
    {
        public String url;
        public String objectType;
        public String id;
        public String displayName;
    }
}

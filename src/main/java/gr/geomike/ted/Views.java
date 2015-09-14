package gr.geomike.ted;

public class Views {

    //all fields
    public static interface Basic {}

    public static interface Bid extends  Basic {}
    public static interface BidInternal extends  Bid {}

    public static interface Bidder extends Basic {}
    public static interface BidderInternal extends Basic {}

    public static interface Seller extends Basic, Item {}
    public static interface SellerInternal extends Seller {}

    public static interface User extends Basic {}

    public static interface Image extends Basic {}

    public static interface Item extends Basic, Bid {}
    public static interface ItemInternal extends Item {}
    public static interface Category extends Basic {}
    public static interface Location {}

    public static interface Message extends Basic {}

    public static interface SellerAll extends SellerInternal, Item, Bid, Bidder {}
}

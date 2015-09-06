package gr.geomike.ted;

public class Views {

    //all fields
    public static interface Basic {}

    public static interface Bid extends  Basic {}
    public static interface BidInternal extends  Bid {}

    public static interface Bidder extends Basic {}
    public static interface BidderInternal extends Basic {}

    public static interface Seller extends Basic {}
    public static interface SellerInternal extends Basic {}

    public static interface User extends Basic {}

    public static interface Item extends Basic, Bid {}
    public static interface Category extends Basic {}
    public static interface Location {}
}

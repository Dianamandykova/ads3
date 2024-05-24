public class TestBST {
    public static void main(String[] args) {
        BST<Integer, String> mybst = new BST<>();

        mybst.insert(1, "smth");
        mybst.insert(102, "another");
        mybst.insert(902, "Tennis");
        mybst.insert(52, "Hockey");
        mybst.insert(312, "Basketball");
        mybst.insert(321, "Football");
        mybst.insert(122, "Formula 1");

        var tree=mybst.iterator();

        for (var element : tree) {
            System.out.println("Key: " + element.getKey() + " Value: " + element.getVal());
        }
    }
}
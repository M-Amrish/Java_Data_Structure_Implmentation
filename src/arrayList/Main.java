package arrayList;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> arr = new CustomArrayList<>();

        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);

        for (int i =0; i<arr.size(); i++){
            System.out.print(arr.get(i)+" ");
        }

        arr.remove(2);
        System.out.println();
        System.out.println("After removing element in arrayList");

        for (int i =0; i<arr.size(); i++){
            System.out.print(arr.get(i)+" ");
        }

    }
}

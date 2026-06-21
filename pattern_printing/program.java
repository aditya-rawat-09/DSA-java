public class program {

    public static void main(String[] args) {
        // find the largest element in an array
        int arr[] = { 2, 5, 1, 9, 6 };
        int largest = arr[0];
        for (int i = 0; i < arr.length; i++) {
        if (arr[i] > largest) {
        largest = arr[i];
        }
        }
        System.out.println("The largest element in the array is: " + largest);


        // calculate the sum of all elements in an array
        int arr2[] = { 1,2,3,4,5 };
        int sum = 0;
        for (int i = 0; i < arr2.length; i++) {
        sum += arr2[i];
        }
        System.out.println("The sum is: " + sum);


        // check if an arrey is sorted in ascending order
        int arr3[] = { 1, 2, 3, 5, 4 };
        boolean isSorted = true;
        for (int i = 0; i < arr3.length -1; i++) {
            if (arr3[i] > arr3[i + 1]) {
                isSorted = false;
                break;
            }
        }
        System.out.println(isSorted);


        // reverse an array without using a temporary array
        int arr4[] = { 1, 2, 3, 4};
        int start = 0;
        int end = arr4.length - 1;
        while (start < end) {
            int temp = arr4[start];
            arr4[start] = arr4[end];
            arr4[end] = temp;
            start++;
            end--;
        }
        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i] + " ");
        }
       
        
        // find the second largest element in an array
        int arr5[]={10,5,20,8};




        // find the first repeating element in an array
        int arr6[] = { 1, 5, 3, 4, 3, 5, 6 };
        for (int i = 0; i < arr6.length; i++) {
            for (int j = i + 1; j < arr6.length; j++) {
                if (arr6[i] == arr6[j]) {
                    System.out.println("The first repeating element is: " + arr6[i]);
                    return;
                }            
            }
        }

    // conut even and odd numbers in an array
    int arr7[] = { 1, 2, 3, 4, 5 };
    int even = 0;
    int odd = 0;
    for (int i = 0; i < arr7.length; i++) {
        if (arr7[i] % 2 == 0) {
            even++;
        } else {
            odd++;
        }
    }
    System.out.println("even= "+even);
    System.out.println("odd= " +odd);


    // count how many times a given element appears in an array
    int arr8[] = {  2, 3, 4, 2, 2 ,5 };
    int count = 0;
    int key =2;
    for (int i = 0; i < arr8.length; i++) {
        if (arr8[i] == key) {
            count++;
        }
    }
    System.out.println(count);

}
}

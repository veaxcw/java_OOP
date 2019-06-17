package com.chengw.algorithm.sort;





import java.util.Random;

public class Sort{

    private int[] sort = new int[SIZE];

    public static final int SIZE = 100;

    private void init(){
        GenricDemo(SIZE);
    }

    private void GenricDemo(int length){//生成基于数组的排序Demom
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        for (int i = 0; i < length; i++){
            sort[i] = random.nextInt(sort.length*5);
        }
    }

    public void bumbleSort(){//冒泡排序
        init();
        long start = System.currentTimeMillis();
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE - i - 1; j++){
                if(sort[j] > sort[j+1]){
                    swap(sort,j,j+1);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println( "bumbleTime:" + (System.currentTimeMillis() - start) + "ms");
        output();

    }

    public void selectSort(){//选择排序
        init();
        long start = System.currentTimeMillis();
        for(int i = 0; i < SIZE;i++){
            for(int j = i+1; j < SIZE;j++){
                if(sort[j] < sort[i]){
                    swap(sort,j,i);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println( "SelectTime:" + (end - start) + "ms");
        output();

    }

    public void insertSort(){//直接插入排序
        init();
        long start = System.currentTimeMillis();
        for(int i = 1;i < SIZE; i++){
           int temp = sort[i];
           int j = i - 1;
           for(;j >= 0;j--){
               if(sort[j] < temp){
                   //dataStructure.sort[j]<temp  目的是找到要插入的位置
                   break;
               }
               /**后移**/
               sort[j+1] = j;
           }
           sort[j+1] = temp;
        }
        long end = System.currentTimeMillis();
        System.out.println( "InsertTime:" + (end - start) + "ms");
        output();
    }

    public void ShellSort(){//希尔排序
        init();
        long start = System.currentTimeMillis();
        int gap = SIZE/2;
        int flag;
        for(;gap > 0;gap/=2){
            for(int j = 0;j + gap < SIZE;j++){
                for(int k = j;k + gap < SIZE;k+=gap){
                    if(sort[k] > sort[k+gap]){
                        swap(sort,k,k+gap);
                    }

                }
            }

        }
        long end = System.currentTimeMillis();
        System.out.println( "ShelltTime:" + (end - start) + "ms");
        output();

    }



    public void QuickSort(){//todo
        init();
        long start = System.currentTimeMillis();
        quickSort(sort,0,SIZE-1);
        long end = System.currentTimeMillis();
        System.out.println( "QuickTime:" + (end - start) + "ms");
        output();


    }

    int flag = 0;

    private void quickSort(int[] list,int left,int right){
        int i = left;
        int j = right-1;

        if( i > j )
            return;
        System.out.println(flag++);
        int pivot = list[right];

        while(i < j ){

            while(list[i] < pivot && i < j)//找到比privot 大的数
                i++;
            while (list[j] > pivot && i < j)
                j--;
            swap(list,i,j);
        }
        swap(list,j,right);
        quickSort(list,left,i-1);
        quickSort(list,j+1,right);
    }




    private void output(){
        int j = 1;
        for (int i:sort) {
            System.out.print(i + " ");

            j++;
            if( j%15 == 0)
                System.out.println("\n");
        }
        System.out.println("\n");
    }

    private void swap(int[] ints,int a,int b){
        int temp = ints[a];
        ints[a] = ints[b];
        ints[b] = temp;
    }



    /*
    public void test1(){
        int n = 50;
        long start = System.currentTimeMillis();
        BigInteger result =  Fibonacci(BigInteger.valueOf(n));
        long end = System.currentTimeMillis() - start;
        System.out.println("第" + n + "个数：" + result);
        System.out.println("time:" + end);
    }

    public BigInteger Fibonacci(BigInteger n){
        int flag;
        if( (flag = n.compareTo(BigInteger.valueOf(0))) == 0)
            return BigInteger.valueOf(1);
        else if((flag = n.compareTo(BigInteger.valueOf(1))) == 0 || (flag = n.compareTo(BigInteger.valueOf(2))) == 0)
            return BigInteger.valueOf(1);
        else
            return Fibonacci(n.subtract(BigInteger.valueOf(1))).add(Fibonacci(n.subtract(BigInteger.valueOf(2)))) ;
    }*/


}

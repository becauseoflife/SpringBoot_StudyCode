package com.muzi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot04WebDemoApplicationTests {

    @Test
    void contextLoads() {
        int[] A = {1, 2, 3};
        int[] B = {1, 2};
    }

    public void merge(int A[], int m, int B[], int n) {
        int[] res = new int[m + n];
        int a=0;
        int b=0;
        int r=0;
        while(a!=m || b!=n){
            if(A[a] < B[b]){
                res[r] = A[a];
                a++;
            }else{
                res[r] = B[b];
                b++;
            }
            r++;
        }

        while(a != m){
            res[r] = A[a];
            a++;
            r++;
        }

        while(b != n){
            res[r] = B[b];
            b++;
            r++;
        }
        System.out.println(res);
    }

}

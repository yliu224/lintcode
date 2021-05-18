package mock_state.lc1885_e;

import java.math.BigInteger;
import java.util.*;

public class Solution {
    /**
     * @param mapping: a list of integer
     * @param nums: a list of string
     * @return: return array of original values
     */
    public List<String> strangeSort(List<Integer> mapping, List<String> nums) {
        Map<BigInteger,List<String>> originalValue = new HashMap<>();
        TreeSet<BigInteger> numberOrder = new TreeSet<>();

        for(int i=0;i<nums.size();i++){
            BigInteger newNumber = getNewNumber(mapping,nums.get(i));
            List<String> numberList = originalValue.getOrDefault(newNumber, new ArrayList<>());
            numberList.add(nums.get(i));
            originalValue.put(newNumber,numberList);
            numberOrder.add(newNumber);
        }

        List<String> answer = new ArrayList<>();

        for(BigInteger n:numberOrder){
            answer.addAll(originalValue.get(n));
        }
        return answer;
    }

    private BigInteger getNewNumber(List<Integer> mapping, String num){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num.length();i++){
            sb.append(mapping.indexOf(Integer.parseInt(num.substring(i,i+1))));
        }
        return new BigInteger(sb.toString());
    }
}

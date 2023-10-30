import java.util.ArrayList;
import java.util.List;

/*
this solution use mutiple loops with recursion
next loops in on the next recursion
it loops the same array but first loop walks the whold array, second loop visits one less element
 the last loops only can visit one element because each recursion will block one element.
 */

/*
1. use int[] instead of List<Integer> for nums because this array size has been predetermined and wont be changed
2. initialize boolean[] with new boolean[nums.length]
3. create a copy before add it to result because java will use reference.
4. java use same for control syntax as c++
 */

public class PermutationSolver {
    List<List<Integer>> permute(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        permuteUtil(result, current, used, nums);

        return result;
    }

    void permuteUtil(List<List<Integer>> result, List<Integer> current, boolean[] used, int[] nums){
        if (current.size() == used.length){
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++)
        {
            if (!used[i]){
                used[i] = true;
                current.add(nums[i]);
                permuteUtil(result, current, used, nums);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }
}

package algoExpert.Stack;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write a Min Max Stack class.
 * The class should support pushing and popping values on and off the stack,
 * peeking at values at the top of the stack,
 * and getting both the minimum and the maximum values in the stack.
 * All class methods, when considered independently, should run in constant time and with constant space.
 * <p>
 * Sample input:
 * -> push(5)
 * -> getMin()
 * -> getMax()
 * -> peek()
 * -> push(7)
 * -> getMin()
 * -> getMax()
 * -> peek()
 * -> push(2)
 * -> getMin()
 * -> getMax()
 * -> peek()
 * -> pop()
 * -> pop()
 * -> getMin()
 * -> getMax()
 * -> peek()
 * Sample output:
 * -> (push 5)
 * -> 5 (min)
 * -> 5 (max)
 * -> 5 (peek)
 * -> (push 7)
 * -> 5 (min)
 * -> 7 (max)
 * -> 7 (peek)
 * -> (push 2)
 * -> 2 (min)
 * -> 7 (max)
 * -> 2 (peek)
 * -> 2 (pop)
 * -> 7 (pop)
 * -> 5 (min)
 * -> 5 (max)
 * -> 5 (peek)
 */
public class MinMaxStackProgram {
    static class MinMaxStack {
        ArrayList<HashMap<String, Integer>> minMaxStack = new ArrayList<HashMap<String, Integer>>();
        ArrayList<Integer> stack = new ArrayList<Integer>();

        // O(1) time | O(1) space
        public Integer peek() {
            return stack.get(stack.size() - 1);
        }

        // O(1) time | O(1) space
        public Integer pop() {
            minMaxStack.remove(minMaxStack.size() - 1);
            return stack.remove(stack.size() - 1);
        }

        // O(1) time | O(1) space
        public void push(Integer number) {
            HashMap<String, Integer> newMinMax = new HashMap<String, Integer>();
            newMinMax.put("min", number);
            newMinMax.put("max", number);
            if (minMaxStack.size() > 0) {
                HashMap<String, Integer> lastMinMax = new HashMap<String, Integer>(
                        minMaxStack.get(minMaxStack.size() - 1)
                );
                newMinMax.replace("min", Math.min(lastMinMax.get("min"), number));
                newMinMax.replace("max", Math.max(lastMinMax.get("max"), number));
            }
            minMaxStack.add(newMinMax);
            stack.add(number);
        }

        // O(1) time | O(1) space
        public Integer getMin() {
            return minMaxStack.get(minMaxStack.size() - 1).get("min");
        }

        // O(1) time | O(1) space
        public Integer getMax() {
            return minMaxStack.get(minMaxStack.size() - 1).get("max");
        }
    }
}

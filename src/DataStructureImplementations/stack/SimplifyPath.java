package DataStructureImplementations.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author guoyifeng
 * Given an absolute path for a file (Unix-style), simplify it.

	For example,
	path = "/home/", => "/home"
	path = "/a/./b/../../c/", => "/c"
	
	Corner Cases:
	
	Did you consider the case where path = "/../"?
	In this case, you should return "/".
	Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
	In this case, you should ignore redundant slashes and return "/home/foo".

 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return path;
        }
        Deque<String> stack = new ArrayDeque<>();
        Set<String> skip = new HashSet<>(Arrays.asList(".", "..", ""));
        // /a/./b/../../c/
        // a  . b .. .. c 
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!skip.contains(dir)) {
                stack.push(dir);
            }
        }
        // stack: || c
        String res = "";
        for (String dir : stack) {
           res = "/" + dir + res; // return absolute path starting with "/"
        }
        return stack.isEmpty() ? "/" : res;
    }
}
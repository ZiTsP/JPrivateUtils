package zitsp.putils.textmanipulation;

import java.util.Optional;

import zitsp.putils.CodingLanguage;
import zitsp.putils.CodingLanguages;

public class CommentOut {
    
    public static boolean doesIncludeCommentLine(String str, CodingLanguage language) {
        Optional<String> comment = language.getCommentDelimiter();    
        if (comment.isPresent() && str.indexOf(comment.get()) >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public static String removeCommentLine(String str, CodingLanguage language) {
        Optional<String> comment = language.getCommentDelimiter();   
        if (comment.isPresent()) {
            int index = str.indexOf(comment.get());
            if (0 == index) {
                return "";
            } else if (0 < index) {
                return str.substring(0, index);
            } else {
                return str;
            }
        } else {
            return str;
        }
    }
    
    public static void main(String[] args) {
        String str = "#bar bar //foo foo";
        System.out.println(CommentOut.doesIncludeCommentLine(str, CodingLanguages.JAVA));
        System.out.println(CommentOut.removeCommentLine(str, CodingLanguages.JAVA));
        System.out.println(CommentOut.doesIncludeCommentLine(str, CodingLanguages.PERL));
        System.out.println(CommentOut.removeCommentLine(str, CodingLanguages.PERL));
        System.out.println(CommentOut.doesIncludeCommentLine(str, CodingLanguages.EMPTY));
        System.out.println(CommentOut.removeCommentLine(str, CodingLanguages.EMPTY));
    } 
    
}

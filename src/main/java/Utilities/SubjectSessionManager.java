package Utilities;

/**
 *
 * @author sherr
 */
public class SubjectSessionManager {

    private static int currentSubjectId;

    public static void setCurrentSubjectId(int subjectId) {
        currentSubjectId = subjectId;
        System.out.println("Setting currentSubjectId: " + subjectId);
    }

    public static int getCurrentSubjectId() {
        System.out.println("Getting currentSubjectId: " + currentSubjectId);
        return currentSubjectId;
    }
}

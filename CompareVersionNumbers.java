public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String v1[] = version1.split("\\.");
        String v2[] = version2.split("\\.");
        
        int n = v1.length, m = v2.length;
        int i=0;
        for (i = 0; i < Math.min(n, m); i++) {
            int n1 = Integer.parseInt(v1[i]);
            int n2 = Integer.parseInt(v2[i]);
            //System.out.println(n1 + " " + n2);
            if (n1 != n2) {
                return (n1 > n2) ? 1 : -1;
            }
        }
        
        if (n==m)
            return 0;
        
        if (n>m) {
           while (i < n) {
               if (Integer.parseInt(v1[i]) != 0) {
                   return 1;
               }
               i++;
           }
            return 0;
        } else {
          while (i < m) {
               if (Integer.parseInt(v2[i]) != 0) {
                   return -1;
               }
               i++;
           }
            return 0;  
        }
    }
}

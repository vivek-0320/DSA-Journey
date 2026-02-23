public class first {
    public static int vowelConsonantScore(String s) {
        int c=0;
        int v=0;
        for(char ch:s.toCharArray())
        {

            if("aeiou".indexOf(ch) >= 0)
            {
                System.out.println(ch+" is a v");
                v++;
            }       
            else if(Character.isLetter(ch))
            {
                System.out.println(ch+" is a c");
                 c++;
            }
            else
                continue;
        }
        System.out.println("c: "+c+", v: "+v);
        int score = 0;
        if(c>0)
            score = (int)Math.floor(v/c);
        return score;
    }

    
    public static void main(String[] args) {
    }
}

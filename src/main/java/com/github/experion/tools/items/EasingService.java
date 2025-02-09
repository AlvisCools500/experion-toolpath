package com.github.experion.tools.items;

// CREDIT GITHUB: Michaelangel007/easing/blob/master/js/core/easing.js
// CHANGED SOME KEYWORDS IN CODE EASILY WITH SEARCH REPLACE
public class EasingService {
    public static double InBack         (double p) { double              k = 1.70158        ;              return p*p*(p*(k+1) - k);                                        };
    public static double InOutBack      (double p) { double m=p-1;double t=p*2, k = 1.70158 * 1.525; if (p < 0.5) return p*t*(t*(k+1) - k); else return 1 + 2*m*m*(2*m*(k+1) + k); }; // NOTE: Can go negative! i.e. p = 0.008
    public static double OutBack        (double p) { double m=p-1;double k = 1.70158        ;return 1 +   m*m*(  m*(k+1) + k); };

    // NOTE: 'Exponent2' needs clamping for 0 and 1 respectively
    public static double InExponent2    (double p) {   if (p <= 0) return 0; return   Math.pow( 2,  10*(p-1) ); };
    public static double InOutExponent2 (double p) {
        if (p <= 0) return 0;
        if (p >= 1) return 1;
        if (p <0.5) return             Math.pow( 2,  10*(2*p-1)-1);
        else        return           1-Math.pow( 2, -10*(2*p-1)-1);
    };
    public static double OutExponent2   (double p)  {   if (p >= 1) return 1; return 1-Math.pow( 2, -10* p    ); };
}

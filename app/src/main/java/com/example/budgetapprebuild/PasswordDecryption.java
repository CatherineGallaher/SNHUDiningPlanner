package com.example.budgetapprebuild;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordDecryption {

    public static String decryptAES(String strToDecrypt) {
        try
        {
            //ReadWriteFiles read = new ReadWriteFiles();
            //String[] text = read.readFile();

            String key = "r/1Ess-W`n,XRoQjU`w}duNY1go-P3FBXA]PGE2nPtoMf1amwY6jgF0N.s4o[i`|.1H/pXVJRmN;lY/CR`ZAn\\B=O|@s]poN?8=H-2_HZ?p0YxYZP?;TKs@1pSMX[ZRbzHpobtgm]S@V0]-DUV./_[xi0EfJfV;H^+L;hl4.Nj46,LywSs<`V@yNlWjPCcp2Yp@PJ?iij3_Ixj3[kOL7Qur6s]0ZUbrh}ozxDJp:uqGnxkcI4CNPWKkx[ZxTR1N5s:Huw7E:Ck0QVS;LEKLw]:O6U\\WYWKO;f_DaUthVCnfgQM_u,>Q7Y;Pm7|Hh+],:`[v9SJ0Hvcr>N0TJ|hkce>G\\Pj<,+lE1acUX-??oMhRuH`CSVV<7tx4Pv_A4atGpF^RWmjmWn:>6AGtgnGqQ{PQYrq|jKS6+CR8KB9_<l`pu`GrA,K=^j0Yd+O3vo|LlBt3,g9fA6h_6@.[dyjUW8o[`9,_6,b5H||<9Q:\\TvE`s@Y>9XR6Nh-+i45+qsh=f0Sr|yYHhZhl0uvKctxISjopq>mUeKBka|<[PgZdAvLy6erGEon;7`:./=k?Kg]=Cq|+BmD-,n?Ph3.R;w6^^@o.{kk60;/;KwC?yq-xQXpJh.-XGf;s/32XN5:7{Rq5a0MJ|1OZcjq-3/vT6iKz,5><4pc[LqTr,K{s/b,/znN_Zo{]N;t/cV8J263X,^Xh,JAN?lLmb-mB+=:c5;gKhRb0K}Odomtx5In3I,BMvGQwSJ;g_n-?OC[2i}1`vDk]FTaLfocrqLPl4x}.{VsyO.\\/A^|P6@VT6?XJ5nL;\\e7G[1j@cf},XH]8J{.SU`o7Ow,++DiKi@/}C@uqiKHhp/Hv1]^p:k4QHq6xN8Qo.y2rLgj3<|nX{TFUR<{lw+rDIut,UL@<aEw<kFJ}w=KngH7]o+h.S<`+SY-5Arb1RjbBq?@0GY@FU.a^kmutlzD.za0gNku[Gh0L{PaVQCde4a<\\{xHHilk]y<H:CmT+23QqAKoJ4m4Rn^Zp{sJBs:{y7";

            String PUBLIC = "6gfMBZvc@kfQ/k?1/[m5OQ7{|],lfa7EG7Yf,6+p7LNW:lZY_VXA]Dxq97{qUKJ+1Sp8_FdWFWNcVEP3}_hE,Qf.kKPPlmMFp_RJEdpbQ[T}<.q<P+R35XM>vNMCc:fs0;x1KL153pR:AfaPD?oLVE>UyOxcA^2Va@zOYHJzV5TPNy,FQ>Yx8GU:k0iAvZy5<}<j9HlFgbvNB{8/bdK;gc^kJ4ALjAF5PtT<huJk+PDA1^.]]1MNhtZPo@LCF8fyyic\\Gd|vYk]YlhP,wp]gcRYhBb=n_WBHA;X5^thorRAqMr0HuLO8:e{h=c{NKKrz.IQCWV\\foGYUO[<=f65m^{sshcbF854ta=D.=wiK0T/v+`R|C{+T7u[GhoBtyo.`ux-T?T+L.a^vKPjh`9cd97d>O`KSPowe|?-l[_9t\\rsjU<P7zc2rjAVVwpV[:Y9LQyB[xY|_mSA,;g5PJ:8cF5OHid}R08mr[6/<KQpbjw{?YylJF5A|P/s7h2kncJlr69US`kF>,9}qr^DC0Z3zUaQaOfDCz7`2S.VFye{TrTB4stx^q^[.{R3;5WLq,bT|Zi9Q{,Lv9<V,WW5-}[xBl<X.Fk6s,E>mgp^RA_35}pC@:tBN6,<.gRC:^S]8]BImAW]eqSS`v3sww@:@pAXoQj5|8Vbrz0SQKIqOijO+.9sNvZ8y1d[b85cPm]Q^;?/G}]mrh]EF\\_tEt|=Itib{\\d9T_fh/rlKWh5asctz0HusRENRPdPAtKR31l6EQ-}DsDlhsLp3:UIb{oW@QdDuEu/1[JZInbfRhmL;VC+cNqZLxv[[U/.Mxxa9D7@O>;cXLtaYlEMVe9r|Z[E/`H4]h/eayMIyy{wh\\m/@pV9T;oreGUxi:ThzZpk9UMdM9Fv[Sv^/?5/d[kd[/5Ox=KM2sifHPSdFeAt>fMMw>`<]_dXz1E<9]RD]5[Ct<C;4f5W;{<ADKAUohktju5Ni.T/r5L]>SpT[Y-k3K`X8bdhCJ-P|v5J@ROxjWJFno,gFYY0I<";

            byte[] iv = { 127, 6, 45, 28, 99, 28, 34, 22, 105, 46, 48, 35, 79, 31, 4, 114 };

            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(key.toCharArray(), PUBLIC.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
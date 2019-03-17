package com.jelsat.constants;

public class FilterConstants {
    public static final String AMENITIES = "amenities";
    public static final String BEDS = "beds";
    public static final String BOOKING_TYPE = "booking type";
    public static final String CANCELLATION_POLICY = "cancellation policy";
    public static final String HOUSE_RULES = "house rules";
    public static final String PRICE_RANGE = "price range";
    public static final String ROOMS = "rooms";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getFilterImage(java.lang.String r2, boolean r3) {
        /*
        r2 = r2.toLowerCase();
        r0 = r2.hashCode();
        switch(r0) {
            case -233026569: goto L_0x0048;
            case 3019794: goto L_0x003e;
            case 80552870: goto L_0x0034;
            case 108698360: goto L_0x002a;
            case 156669207: goto L_0x0020;
            case 665578223: goto L_0x0016;
            case 1839384225: goto L_0x000c;
            default: goto L_0x000b;
        };
    L_0x000b:
        goto L_0x0052;
    L_0x000c:
        r0 = "booking type";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0052;
    L_0x0014:
        r2 = 0;
        goto L_0x0053;
    L_0x0016:
        r0 = "cancellation policy";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0052;
    L_0x001e:
        r2 = 6;
        goto L_0x0053;
    L_0x0020:
        r0 = "amenities";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0052;
    L_0x0028:
        r2 = 4;
        goto L_0x0053;
    L_0x002a:
        r0 = "rooms";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0052;
    L_0x0032:
        r2 = 2;
        goto L_0x0053;
    L_0x0034:
        r0 = "price range";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0052;
    L_0x003c:
        r2 = 1;
        goto L_0x0053;
    L_0x003e:
        r0 = "beds";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0052;
    L_0x0046:
        r2 = 3;
        goto L_0x0053;
    L_0x0048:
        r0 = "house rules";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0052;
    L_0x0050:
        r2 = 5;
        goto L_0x0053;
    L_0x0052:
        r2 = -1;
    L_0x0053:
        r0 = 2131231215; // 0x7f0801ef float:1.8078505E38 double:1.0529681267E-314;
        r1 = 2131231216; // 0x7f0801f0 float:1.8078507E38 double:1.052968127E-314;
        switch(r2) {
            case 0: goto L_0x009b;
            case 1: goto L_0x0091;
            case 2: goto L_0x0087;
            case 3: goto L_0x007d;
            case 4: goto L_0x0073;
            case 5: goto L_0x0069;
            case 6: goto L_0x005f;
            default: goto L_0x005c;
        };
    L_0x005c:
        if (r3 == 0) goto L_0x009f;
    L_0x005e:
        return r1;
    L_0x005f:
        if (r3 == 0) goto L_0x0065;
    L_0x0061:
        r2 = 2131231228; // 0x7f0801fc float:1.8078531E38 double:1.052968133E-314;
        return r2;
    L_0x0065:
        r2 = 2131231227; // 0x7f0801fb float:1.807853E38 double:1.0529681326E-314;
        return r2;
    L_0x0069:
        if (r3 == 0) goto L_0x006f;
    L_0x006b:
        r2 = 2131231271; // 0x7f080227 float:1.8078618E38 double:1.0529681543E-314;
        return r2;
    L_0x006f:
        r2 = 2131231270; // 0x7f080226 float:1.8078616E38 double:1.052968154E-314;
        return r2;
    L_0x0073:
        if (r3 == 0) goto L_0x0079;
    L_0x0075:
        r2 = 2131231196; // 0x7f0801dc float:1.8078466E38 double:1.0529681173E-314;
        return r2;
    L_0x0079:
        r2 = 2131231195; // 0x7f0801db float:1.8078464E38 double:1.052968117E-314;
        return r2;
    L_0x007d:
        if (r3 == 0) goto L_0x0083;
    L_0x007f:
        r2 = 2131231212; // 0x7f0801ec float:1.8078499E38 double:1.052968125E-314;
        return r2;
    L_0x0083:
        r2 = 2131231211; // 0x7f0801eb float:1.8078497E38 double:1.0529681247E-314;
        return r2;
    L_0x0087:
        if (r3 == 0) goto L_0x008d;
    L_0x0089:
        r2 = 2131231345; // 0x7f080271 float:1.8078768E38 double:1.052968191E-314;
        return r2;
    L_0x008d:
        r2 = 2131231344; // 0x7f080270 float:1.8078766E38 double:1.0529681904E-314;
        return r2;
    L_0x0091:
        if (r3 == 0) goto L_0x0097;
    L_0x0093:
        r2 = 2131231331; // 0x7f080263 float:1.807874E38 double:1.052968184E-314;
        return r2;
    L_0x0097:
        r2 = 2131231330; // 0x7f080262 float:1.8078738E38 double:1.0529681835E-314;
        return r2;
    L_0x009b:
        if (r3 == 0) goto L_0x009e;
    L_0x009d:
        return r1;
    L_0x009e:
        return r0;
    L_0x009f:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.constants.FilterConstants.getFilterImage(java.lang.String, boolean):int");
    }
}

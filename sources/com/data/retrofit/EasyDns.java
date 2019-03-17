package com.data.retrofit;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import okhttp3.Dns;
import org.xbill.DNS.Address;

public class EasyDns implements Dns {
    private static final String LIVE_API_HOST = "jelsat.com";
    private static final String LIVE_API_IP = "35.169.175.205";
    private boolean mInitialized;
    private InetAddress mLiveApiStaticIpAddress;

    public List<InetAddress> lookup(String str) throws UnknownHostException {
        init();
        try {
            return Collections.singletonList(Address.getByName(str));
        } catch (UnknownHostException e) {
            if (LIVE_API_HOST.equals(str) != null && this.mLiveApiStaticIpAddress != null) {
                return Collections.singletonList(this.mLiveApiStaticIpAddress);
            }
            throw e;
        }
    }

    private void init() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r8 = this;
        r0 = r8.mInitialized;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = 1;
        r8.mInitialized = r0;
        r1 = "35.169.175.205";	 Catch:{ UnknownHostException -> 0x0011 }
        r1 = java.net.InetAddress.getByName(r1);	 Catch:{ UnknownHostException -> 0x0011 }
        r8.mLiveApiStaticIpAddress = r1;	 Catch:{ UnknownHostException -> 0x0011 }
        goto L_0x0018;
    L_0x0011:
        r1 = "DNS";
        r2 = "Couldn't initialize static IP address";
        android.util.Log.w(r1, r2);
    L_0x0018:
        r1 = org.xbill.DNS.Lookup.getDefaultResolver();	 Catch:{ UnknownHostException -> 0x0048 }
        r2 = new org.xbill.DNS.SimpleResolver;	 Catch:{ UnknownHostException -> 0x0048 }
        r3 = "8.8.8.8";	 Catch:{ UnknownHostException -> 0x0048 }
        r2.<init>(r3);	 Catch:{ UnknownHostException -> 0x0048 }
        r3 = new org.xbill.DNS.SimpleResolver;	 Catch:{ UnknownHostException -> 0x0048 }
        r4 = "8.8.4.4";	 Catch:{ UnknownHostException -> 0x0048 }
        r3.<init>(r4);	 Catch:{ UnknownHostException -> 0x0048 }
        r4 = new org.xbill.DNS.SimpleResolver;	 Catch:{ UnknownHostException -> 0x0048 }
        r5 = "205.251.198.30";	 Catch:{ UnknownHostException -> 0x0048 }
        r4.<init>(r5);	 Catch:{ UnknownHostException -> 0x0048 }
        r5 = new org.xbill.DNS.ExtendedResolver;	 Catch:{ UnknownHostException -> 0x0048 }
        r6 = 4;	 Catch:{ UnknownHostException -> 0x0048 }
        r6 = new org.xbill.DNS.Resolver[r6];	 Catch:{ UnknownHostException -> 0x0048 }
        r7 = 0;	 Catch:{ UnknownHostException -> 0x0048 }
        r6[r7] = r1;	 Catch:{ UnknownHostException -> 0x0048 }
        r6[r0] = r2;	 Catch:{ UnknownHostException -> 0x0048 }
        r0 = 2;	 Catch:{ UnknownHostException -> 0x0048 }
        r6[r0] = r3;	 Catch:{ UnknownHostException -> 0x0048 }
        r0 = 3;	 Catch:{ UnknownHostException -> 0x0048 }
        r6[r0] = r4;	 Catch:{ UnknownHostException -> 0x0048 }
        r5.<init>(r6);	 Catch:{ UnknownHostException -> 0x0048 }
        org.xbill.DNS.Lookup.setDefaultResolver(r5);	 Catch:{ UnknownHostException -> 0x0048 }
        return;
    L_0x0048:
        r0 = "DNS";
        r1 = "Couldn't initialize custom resolvers";
        android.util.Log.w(r0, r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.data.retrofit.EasyDns.init():void");
    }
}

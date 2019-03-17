package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.data.payments.PaymentsDetailsData;
import com.jelsat.R;
import com.jelsat.utils.GlideApp;
import java.util.List;
import java.util.Locale;

public class HostPaymentsDetailsAdapter extends Adapter<HostPaymentsViewHolder> {
    private Context context;
    private List<PaymentsDetailsData> paymentsList;

    public class HostPaymentsViewHolder extends ViewHolder {
        @BindView(2131362249)
        ImageView image_property;
        @BindView(2131362708)
        TextView tv_bookings_count;
        @BindView(2131362754)
        TextView tv_property_address;
        @BindView(2131362761)
        TextView tv_property_name;
        @BindView(2131362762)
        TextView tv_property_payment_status;
        @BindView(2131362784)
        TextView tv_total_earnings;

        public HostPaymentsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class HostPaymentsViewHolder_ViewBinding implements Unbinder {
        private HostPaymentsViewHolder target;

        @UiThread
        public HostPaymentsViewHolder_ViewBinding(HostPaymentsViewHolder hostPaymentsViewHolder, View view) {
            this.target = hostPaymentsViewHolder;
            hostPaymentsViewHolder.image_property = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_property, "field 'image_property'", ImageView.class);
            hostPaymentsViewHolder.tv_property_name = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_property_name, "field 'tv_property_name'", TextView.class);
            hostPaymentsViewHolder.tv_property_address = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_property_address, "field 'tv_property_address'", TextView.class);
            hostPaymentsViewHolder.tv_property_payment_status = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_property_payment_status, "field 'tv_property_payment_status'", TextView.class);
            hostPaymentsViewHolder.tv_bookings_count = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_bookings_count, "field 'tv_bookings_count'", TextView.class);
            hostPaymentsViewHolder.tv_total_earnings = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_total_earnings, "field 'tv_total_earnings'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            HostPaymentsViewHolder hostPaymentsViewHolder = this.target;
            if (hostPaymentsViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            hostPaymentsViewHolder.image_property = null;
            hostPaymentsViewHolder.tv_property_name = null;
            hostPaymentsViewHolder.tv_property_address = null;
            hostPaymentsViewHolder.tv_property_payment_status = null;
            hostPaymentsViewHolder.tv_bookings_count = null;
            hostPaymentsViewHolder.tv_total_earnings = null;
        }
    }

    public HostPaymentsDetailsAdapter(List<PaymentsDetailsData> list, Context context) {
        this.paymentsList = list;
        this.context = context;
    }

    @NonNull
    public HostPaymentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HostPaymentsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.host_payments_single_row, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull HostPaymentsViewHolder hostPaymentsViewHolder, int i) {
        hostPaymentsViewHolder.tv_property_name.setText(((PaymentsDetailsData) this.paymentsList.get(i)).getPropertyName());
        hostPaymentsViewHolder.tv_property_address.setText(((PaymentsDetailsData) this.paymentsList.get(i)).getAddress());
        if (((PaymentsDetailsData) this.paymentsList.get(i)).getPaymentStatus() == null || ((PaymentsDetailsData) this.paymentsList.get(i)).getPaymentStatus().equalsIgnoreCase("")) {
            hostPaymentsViewHolder.tv_property_payment_status.setVisibility(8);
        } else {
            hostPaymentsViewHolder.tv_property_payment_status.setText(((PaymentsDetailsData) this.paymentsList.get(i)).getPaymentStatus());
        }
        hostPaymentsViewHolder.tv_bookings_count.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(((PaymentsDetailsData) this.paymentsList.get(i)).getBookings())}));
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            hostPaymentsViewHolder.tv_total_earnings.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(((PaymentsDetailsData) this.paymentsList.get(i)).getEarnings()), this.context.getString(R.string.saudi_currency)}));
        } else {
            hostPaymentsViewHolder.tv_total_earnings.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{this.context.getString(R.string.saudi_currency), Float.valueOf(((PaymentsDetailsData) this.paymentsList.get(i)).getEarnings())}));
        }
        GlideApp.with(this.context).load(((PaymentsDetailsData) this.paymentsList.get(i)).getImage()).placeholder((int) R.drawable.default_logo).apply(new RequestOptions().transform(new RoundedCorners(8))).error((int) R.drawable.default_logo).into(hostPaymentsViewHolder.image_property);
    }

    public void setData(List<PaymentsDetailsData> list) {
        this.paymentsList = list;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.paymentsList != null ? this.paymentsList.size() : 0;
    }
}

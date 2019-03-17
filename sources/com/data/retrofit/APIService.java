package com.data.retrofit;

import com.data.SeedAndLanguage.ChangeLanguageResponse;
import com.data.SeedAndLanguage.SendDeviceTokenRequest;
import com.data.SystemConnect;
import com.data.addproperty.AddPropertyRequest;
import com.data.addproperty.AddPropertyResponse;
import com.data.addproperty.DeleteImagerequest;
import com.data.addproperty.ImageUploadResponse;
import com.data.alllocalities.AllLocalitiesResponse;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.applycoupon.ApplyCouponRequest;
import com.data.applycoupon.ApplyCouponResponse;
import com.data.bankdetails.AddBankDetailsRequest;
import com.data.bankdetails.BankDetailsResponse;
import com.data.bookings.BookingCancelRequest;
import com.data.bookings.BookingCancelResponse;
import com.data.bookings.BookingsResponse;
import com.data.changepassword.ChangePasswordRequest;
import com.data.cretepassword.CreatePasswordRequest;
import com.data.dashboardhome.DashBoardHomeResponse;
import com.data.feedback.FeedbackCategoryResponse;
import com.data.feedback.SubmitFeedbackRequest;
import com.data.generateotp.GenerateOTPRequest;
import com.data.generateotp.UpdateMobileNumberRequest;
import com.data.help.Answer;
import com.data.help.Faq;
import com.data.help.GetAnswerRequest;
import com.data.hostbookings.HostBookingsResponse;
import com.data.inbox.InboxMergeResponse;
import com.data.inbox.InboxMessageData;
import com.data.inbox.InboxNotificationSectionModel;
import com.data.inbox.MessageHistoryRequest;
import com.data.inbox.ReadNotificationRequest;
import com.data.inbox.SendMessageRequest;
import com.data.inbox.SendMessageRequestHost;
import com.data.messagehistory.MessagesData;
import com.data.paymenthistory.PaymentHistoryResponse;
import com.data.payments.HostPaymentsResponse;
import com.data.payments.HostProperty;
import com.data.payments.totalbookings.HostBookingSectionModel;
import com.data.profile.GetGuestProfileRequest;
import com.data.profilefeedback.SubmitGuestFeedbackRequest;
import com.data.propertybook.PropertyBookResponse;
import com.data.propertycostcalendar.PropertyCostCalendarRequest;
import com.data.propertycostcalendar.PropertyCostCalendarResponse;
import com.data.propertycostcalendar.UpdateCalendarRequest;
import com.data.propertydetail.PropertyDetailRequest;
import com.data.propertydetail.PropertyDetailResponse;
import com.data.propertyfavourite.PropertyFavouriteRequest;
import com.data.propertyfavourite.PropertyFavouriteResponse;
import com.data.propertyfeedback.SubmitPropertyFeedbackRequest;
import com.data.propertylisting.DeletePropertyRequest;
import com.data.propertylisting.PropertyListingResponse;
import com.data.propertypayment.PropertyPaymentRequest;
import com.data.propertypayment.PropertyPaymentResponse;
import com.data.propertypricing.PropertyPricingRequest;
import com.data.propertyreviews.PropertyReviewsResponse;
import com.data.publicprofile.PublicProfileResponse;
import com.data.publishproperty.PublishPropertyRequest;
import com.data.publishproperty.PublishPropertyResponse;
import com.data.sdktoken.SDKTokenRequest;
import com.data.sdktoken.SDKTokenResponse;
import com.data.searchproperty.SearchProperty;
import com.data.searchproperty.SearchPropertyRequest;
import com.data.searchproperty.SearchPropertyResponse;
import com.data.searchtoplocalities.SearchTopLocality;
import com.data.signin.SaveFcmTokenRequest;
import com.data.signin.SignInRequest;
import com.data.signin.SignInResponse;
import com.data.signup.SignUpRequest;
import com.data.signup.SignUpResponse;
import com.data.socialsignin.SocialSignInRequest;
import com.data.socialsignup.SocialSignUpRequest;
import com.data.socialsignup.SocialSignUpResponse;
import com.data.support.SupportRequest;
import com.data.updateprofile.UpdateProfileResponse;
import com.data.validateotp.ValidateOTPRequest;
import com.data.viewbill.PropertyViewBillRequest;
import com.data.viewbill.PropertyViewBillResponse;
import com.data.wallet.DeleteSavedCardRequest;
import com.data.wallet.WalletResponse;
import io.reactivex.Single;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {
    @POST("property/abortbooking")
    Single<ResponseBody> abortBooking(@Body JSONObject jSONObject);

    @POST("bankdetails")
    Single<BankDetailsResponse> addBankDetails(@Body AddBankDetailsRequest addBankDetailsRequest);

    @POST("property/coupon")
    Single<ApplyCouponResponse> applyCoupon(@Body ApplyCouponRequest applyCouponRequest);

    @POST("host")
    Single<ResponseBody> becomeHost();

    @POST("guestcancel")
    Single<BookingCancelResponse> cancelBookingByGuest(@Body BookingCancelRequest bookingCancelRequest);

    @POST("hostcancel")
    Single<BookingCancelResponse> cancelBookingByHost(@Body BookingCancelRequest bookingCancelRequest);

    @POST("changelanguage")
    Single<ChangeLanguageResponse> changeLanguage(@Body SendDeviceTokenRequest sendDeviceTokenRequest);

    @POST("changepassword")
    Single<ResponseBody> changePassword(@Body ChangePasswordRequest changePasswordRequest);

    @POST("acceptbooking")
    Single<BookingCancelResponse> confirmBookingByHost(@Body BookingCancelRequest bookingCancelRequest);

    @POST("hostsendmessage")
    Single<ResponseBody> createHostMessage(@Body SendMessageRequestHost sendMessageRequestHost);

    @POST("createmessage")
    Single<MessagesData> createMessage(@Body SendMessageRequest sendMessageRequest);

    @POST("deleteproperty")
    Single<ResponseBody> deleteProperty(@Body DeletePropertyRequest deletePropertyRequest);

    @POST("property/deleteimage")
    Single<ResponseBody> deletePropertyImage(@Body DeleteImagerequest deleteImagerequest);

    @POST("deletecard")
    Single<ResponseBody> deleteSavedCard(@Body DeleteSavedCardRequest deleteSavedCardRequest);

    @POST("forgot/password")
    Single<ResponseBody> doForgotPassword(@Body GenerateOTPRequest generateOTPRequest);

    @POST("forgot/validate")
    Single<ResponseBody> doForgotValidateOTP(@Body ValidateOTPRequest validateOTPRequest);

    @POST("property/payment")
    Single<PropertyPaymentResponse> doPropertyPayment(@Body PropertyPaymentRequest propertyPaymentRequest);

    @POST("hybridauth/login")
    Single<SignInResponse> doSocialLogin(@Body SocialSignInRequest socialSignInRequest);

    @POST("hybridauth/register")
    Single<SocialSignUpResponse> doSocialSignUp(@Body SocialSignUpRequest socialSignUpRequest);

    @POST("forgot/update")
    Single<ResponseBody> doUpdatePassword(@Body CreatePasswordRequest createPasswordRequest);

    @POST("faq")
    Single<List<Answer>> faqAnswer(@Body GetAnswerRequest getAnswerRequest);

    @GET("faq/guest")
    Single<List<Faq>> faqGuest();

    @GET("faq/host")
    Single<List<Faq>> faqHost();

    @GET("feedback/guest")
    Single<List<FeedbackCategoryResponse>> feedbackGuest();

    @GET("feedback/host")
    Single<List<FeedbackCategoryResponse>> feedbackHost();

    @POST("property/search")
    Single<SearchPropertyResponse> fetchSearchResults(@Body SearchPropertyRequest searchPropertyRequest);

    @POST("otp/generate")
    Single<ResponseBody> generateOTP(@Body GenerateOTPRequest generateOTPRequest);

    @POST("dashboard/alllocalities")
    Single<List<AllLocalitiesResponse>> getAllLocalities(@Body JSONObject jSONObject);

    @POST("property/add")
    Single<AddPropertyResponse> getAllPropertyDeatils(@Body AddPropertyRequest addPropertyRequest);

    @GET("bankdetails")
    Single<BankDetailsResponse> getBankDetails();

    @GET("guestconversations")
    Single<List<InboxMessageData>> getConversationsHistory();

    @POST("system/connect")
    Single<SystemConnect> getCookie();

    @POST("dashboard/index")
    Single<DashBoardHomeResponse> getDashBoardData(@Body JSONObject jSONObject);

    @GET("guestbooking")
    Single<BookingsResponse> getGuestBookings();

    @GET("guestinbox/1")
    Single<InboxMergeResponse> getGuestInboxResponse();

    @GET("guestnotifications/android")
    Single<List<InboxNotificationSectionModel>> getGuestNotificationsHistory();

    @POST("guestprodetails")
    Single<PublicProfileResponse> getGuestPublicProfile(@Body GetGuestProfileRequest getGuestProfileRequest);

    @POST("guestreview")
    Single<PropertyReviewsResponse> getGuestReviews(@Body SubmitGuestFeedbackRequest submitGuestFeedbackRequest);

    @GET("hostbooking")
    Single<HostBookingsResponse> getHostBookings();

    @GET("hostcancelations")
    Single<List<HostBookingSectionModel>> getHostCancelledProperties();

    @GET("hostconversations")
    Single<List<InboxMessageData>> getHostConversationsHistory();

    @GET("hostinbox/1")
    Single<InboxMergeResponse> getHostInboxResponse();

    @POST("hostmessagedetails")
    Single<MessagesData> getHostMessageHistory(@Body MessageHistoryRequest messageHistoryRequest);

    @GET("hostnotifications/android")
    Single<List<InboxNotificationSectionModel>> getHostNotificationsHistory();

    @GET("hostpayments")
    Single<HostPaymentsResponse> getHostPayments();

    @GET("publishedproperties")
    Single<List<HostProperty>> getHostPublishedProperties();

    @GET("totalhostbooking")
    Single<List<HostBookingSectionModel>> getHostTotalBookings();

    @GET("totalhostearnings")
    Single<List<HostBookingSectionModel>> getHostTotalEarnings();

    @POST("messagedetails")
    Single<MessagesData> getMessageHistory(@Body MessageHistoryRequest messageHistoryRequest);

    @GET("openalllocalities")
    Single<List<AllLocalitiesResponse>> getOpenAllLocalities();

    @GET("opendashboard")
    Single<DashBoardHomeResponse> getOpenDashBoardData();

    @POST("openguestprodetails")
    Single<PublicProfileResponse> getOpenGuestPublicProfile(@Body GetGuestProfileRequest getGuestProfileRequest);

    @POST("property/opencostcalender")
    Single<PropertyCostCalendarResponse> getOpenPropertyCostCalendarDetails(@Body PropertyCostCalendarRequest propertyCostCalendarRequest);

    @POST("property/opendetail")
    Single<PropertyDetailResponse> getOpenPropertyDetail(@Body PropertyDetailRequest propertyDetailRequest);

    @POST("property/openfeedback")
    Single<PropertyReviewsResponse> getOpenPropertyReviews(@Body SubmitPropertyFeedbackRequest submitPropertyFeedbackRequest);

    @POST("property/openseed")
    Single<SeedResponse> getOpenPropertySeed();

    @GET("opentoplocalities")
    Single<SearchTopLocality> getOpenTopLocalitiesData();

    @POST("sdktoken")
    Single<SDKTokenResponse> getPayfortSdkToken(@Body SDKTokenRequest sDKTokenRequest);

    @GET("guestpayments")
    Single<PaymentHistoryResponse> getPaymentHistory();

    @POST("property/viewbill")
    Single<PropertyViewBillResponse> getPropertyBill(@Body PropertyViewBillRequest propertyViewBillRequest);

    @POST("property/book")
    Single<PropertyBookResponse> getPropertyBookResponse(@Body PropertyViewBillRequest propertyViewBillRequest);

    @POST("property/costcalender")
    Single<PropertyCostCalendarResponse> getPropertyCostCalendarDetails(@Body PropertyCostCalendarRequest propertyCostCalendarRequest);

    @POST("property/detail")
    Single<PropertyDetailResponse> getPropertyDetail(@Body PropertyDetailRequest propertyDetailRequest);

    @GET("listproperties")
    Single<PropertyListingResponse> getPropertyListingDetails();

    @POST("property/pricing")
    Single<ResponseBody> getPropertyPricing(@Body PropertyPricingRequest propertyPricingRequest);

    @POST("property/feedback")
    Single<PropertyReviewsResponse> getPropertyReviews(@Body SubmitPropertyFeedbackRequest submitPropertyFeedbackRequest);

    @POST("property/seed")
    Single<SeedResponse> getPropertySeed();

    @POST("property/updatestatus")
    Single<PublishPropertyResponse> getPublishPropertyStaus(@Body PublishPropertyRequest publishPropertyRequest);

    @GET("savedproperties")
    Single<List<SearchProperty>> getSavedPropertiesList();

    @POST("dashboard/toplocalities")
    Single<SearchTopLocality> getTopLocalitiesData();

    @POST("user/token")
    Single<SDKTokenResponse> getUserToken();

    @GET("wallet")
    Single<WalletResponse> getWalletData();

    @POST("guestrefund")
    Single<ResponseBody> guestRefund();

    @POST("user/logout")
    Single<ResponseBody> logout(@Body SaveFcmTokenRequest saveFcmTokenRequest);

    @POST("property/opensearch")
    Single<SearchPropertyResponse> openFetchSearchResults(@Body SearchPropertyRequest searchPropertyRequest);

    @POST("user/login")
    Single<SignInResponse> placeUserLogin(@Body SignInRequest signInRequest);

    @POST("user")
    Single<SignUpResponse> placeUserSignUp(@Body SignUpRequest signUpRequest);

    @POST("readnotification")
    Single<ResponseBody> readNotification(@Body ReadNotificationRequest readNotificationRequest);

    @POST("rejectbooking")
    Single<BookingCancelResponse> rejectBookingByHost(@Body BookingCancelRequest bookingCancelRequest);

    @POST("reportproperty")
    Single<ResponseBody> reportProperty(@Body SubmitPropertyFeedbackRequest submitPropertyFeedbackRequest);

    @POST("push_notifications")
    Single<ResponseBody> saveToken(@Body SaveFcmTokenRequest saveFcmTokenRequest);

    @POST("push_notifications")
    Single<ResponseBody> sendFCMTokenToServer(@Body SaveFcmTokenRequest saveFcmTokenRequest);

    @POST("sendmessage")
    Single<ResponseBody> sendMessage(@Body SendMessageRequest sendMessageRequest);

    @POST("property/favourite")
    Single<PropertyFavouriteResponse> setPropertyFavourite(@Body PropertyFavouriteRequest propertyFavouriteRequest);

    @POST("feedback")
    Single<ResponseBody> submitFeedbackGuest(@Body SubmitFeedbackRequest submitFeedbackRequest);

    @POST("hostfeedback")
    Single<ResponseBody> submitProfileFeedback(@Body SubmitGuestFeedbackRequest submitGuestFeedbackRequest);

    @POST("reviewproperty")
    Single<ResponseBody> submitPropertyFeedback(@Body SubmitPropertyFeedbackRequest submitPropertyFeedbackRequest);

    @POST("support")
    Single<ResponseBody> support(@Body SupportRequest supportRequest);

    @POST("otp/updatephone")
    Single<ResponseBody> updateMobileNumber(@Body UpdateMobileNumberRequest updateMobileNumberRequest);

    @POST("profiledetails")
    @Multipart
    Single<UpdateProfileResponse> updateProfileWithImage(@Part MultipartBody.Part part, @Part MultipartBody.Part part2, @Part("form_data") RequestBody requestBody);

    @POST("property/updatecalender")
    Single<PropertyCostCalendarResponse> updatePropertyCalendar(@Body UpdateCalendarRequest updateCalendarRequest);

    @POST("property/uploadimage")
    @Multipart
    Single<ImageUploadResponse> uploadPropertyimage(@Part MultipartBody.Part part, @Part("form_data") RequestBody requestBody);

    @POST("otp/validate")
    Single<ResponseBody> validateOTP(@Body ValidateOTPRequest validateOTPRequest);

    @POST("emailverify")
    Single<ResponseBody> verifyEmail();
}

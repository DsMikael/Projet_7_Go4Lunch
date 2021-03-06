// Generated by view binder compiler. Do not edit!
package com.mdasilva.go4lunch.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mdasilva.go4lunch.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView appName;

  @NonNull
  public final ImageView backgroundLogin;

  @NonNull
  public final Button btnFb;

  @NonNull
  public final Button btnGoogle;

  @NonNull
  public final TextView catchPhrase;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull TextView appName,
      @NonNull ImageView backgroundLogin, @NonNull Button btnFb, @NonNull Button btnGoogle,
      @NonNull TextView catchPhrase) {
    this.rootView = rootView;
    this.appName = appName;
    this.backgroundLogin = backgroundLogin;
    this.btnFb = btnFb;
    this.btnGoogle = btnGoogle;
    this.catchPhrase = catchPhrase;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_name;
      TextView appName = ViewBindings.findChildViewById(rootView, id);
      if (appName == null) {
        break missingId;
      }

      id = R.id.background_login;
      ImageView backgroundLogin = ViewBindings.findChildViewById(rootView, id);
      if (backgroundLogin == null) {
        break missingId;
      }

      id = R.id.btn_fb;
      Button btnFb = ViewBindings.findChildViewById(rootView, id);
      if (btnFb == null) {
        break missingId;
      }

      id = R.id.btn_google;
      Button btnGoogle = ViewBindings.findChildViewById(rootView, id);
      if (btnGoogle == null) {
        break missingId;
      }

      id = R.id.catch_phrase;
      TextView catchPhrase = ViewBindings.findChildViewById(rootView, id);
      if (catchPhrase == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, appName, backgroundLogin, btnFb,
          btnGoogle, catchPhrase);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

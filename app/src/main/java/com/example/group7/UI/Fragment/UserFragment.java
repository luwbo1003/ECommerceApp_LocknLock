package com.example.group7.UI.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.group7.R;
import com.example.group7.ViewModels.UserViewModel;
import com.example.group7.activities.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    UserViewModel userViewModel;
    TextView tv_email;
    Button btn_logout, btn_save;
    EditText ed_pass;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        SharedPreferences mPreferences= getActivity().getSharedPreferences("isLoggin", Context.MODE_PRIVATE);

        tv_email= root.findViewById(R.id.tv_email);
        btn_logout = root.findViewById(R.id.btn_logout);
        ed_pass = root.findViewById(R.id.ed_pass);
        btn_save = root.findViewById(R.id.btn_save);


        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
            String emailUser = args.getString("email");
            tv_email.setText(emailUser);
        }
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = ed_pass.getText().toString();
                userViewModel.updatePassword(id, pass, getContext(), "Đã đổi mật khẩu", "Không thể đổi mật khẩu");
            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(root.getContext().getApplicationContext(), LoginActivity.class);
                // clear preferences
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(intent);
                getActivity().finish();
            }
        });
        // Inflate the layout for this fragment
        return root;
    }
}
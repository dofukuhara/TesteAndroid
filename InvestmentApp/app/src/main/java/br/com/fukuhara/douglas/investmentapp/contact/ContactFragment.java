package br.com.fukuhara.douglas.investmentapp.contact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.fukuhara.douglas.investmentapp.R;
import br.com.fukuhara.douglas.investmentapp.domain.CellItem;
import br.com.fukuhara.douglas.investmentapp.mvp.ContactContract;
import br.com.fukuhara.douglas.investmentapp.util.Type;
import br.com.fukuhara.douglas.investmentapp.util.TypeField;
import br.com.fukuhara.douglas.investmentapp.util.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactFragment extends Fragment implements ContactContract.View {

    private ContactContract.Presenter mPresenter;

    @BindView(R.id.contactFragmentConstraintLayout)
    ConstraintLayout contactFragmentConstraintLayout;
    @BindView(R.id.pg_contact)
    ProgressBar pbContact;
    @BindView(R.id.contact_no_internet)
    TextView tvContactNoInternet;

    @BindView(R.id.tv_contact_message)
    TextView tvContactMessage;
    @BindView(R.id.tip_name_field)
    TextInputLayout tipNameField;
    @BindView(R.id.edt_name_field)
    TextInputEditText edtNameField;
    @BindView(R.id.tip_email_field)
    TextInputLayout tipEmailField;
    @BindView(R.id.edt_email_field)
    TextInputEditText edtEmailField;
    @BindView(R.id.tip_phone_field)
    TextInputLayout tipPhoneField;
    @BindView(R.id.edt_phone_field)
    TextInputEditText edtPhoneField;
    @BindView(R.id.cb_email)
    CheckBox cbEmail;
    @BindView(R.id.btn_send)
    Button btnSend;

    // Empty constructor for AssetFragment class
    public ContactFragment() { }

    public static ContactFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void setPresenter(ContactContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.contact_fragment, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Requesting Presenter to get the Cells info, so that the UI can be properly populated
        mPresenter.start( savedInstanceState );
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // In case that we have the Cell information and the user is rotating the device,
        // let's save this info in the bundle, so that we can retrieve this information after
        // the device finishes the rotating action and save some REST call
        if (mPresenter.getCells() != null) {
            outState.putParcelableArrayList(SAVED_STATE_BUNDLE_CELLS_KEY, mPresenter.getCells());
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public void updateUiWithCellsInfo(ArrayList<CellItem> cells) {
        for (CellItem item : cells) {
            String typeFieldFromItem = item.getTypeField();
            String typeFromItem = item.getType();

            if (!TextUtils.isEmpty(typeFieldFromItem) &&
                    !"null".equals(typeFieldFromItem.toLowerCase())) {

                TypeField typeField = Utils.getTypeField(typeFieldFromItem);
                switch (typeField) {
                    case TEXT:
                        tipNameField.setHint(item.getMessage());
                        if (item.getHidden().toLowerCase().equals("false")) {
                            tipNameField.setVisibility(View.VISIBLE);
                            edtNameField.setVisibility(View.VISIBLE);
                        } else {
                            tipNameField.setVisibility(View.INVISIBLE);
                            edtNameField.setVisibility(View.INVISIBLE);
                        }
                        break;
                    case TELNUMBER:
                        tipPhoneField.setHint(item.getMessage());
                        edtPhoneField.addTextChangedListener(phoneTextWatcher());
                        if (item.getHidden().toLowerCase().equals("false")) {
                            tipPhoneField.setVisibility(View.VISIBLE);
                            edtPhoneField.setVisibility(View.VISIBLE);
                        } else {
                            tipPhoneField.setVisibility(View.INVISIBLE);
                            edtPhoneField.setVisibility(View.INVISIBLE);
                        }
                        break;
                    case EMAIL:
                        tipEmailField.setHint(item.getMessage());
                        if (item.getHidden().toLowerCase().equals("false")) {
                            tipEmailField.setVisibility(View.VISIBLE);
                            edtEmailField.setVisibility(View.VISIBLE);
                        } else {
                            tipEmailField.setVisibility(View.INVISIBLE);
                            edtEmailField.setVisibility(View.INVISIBLE);
                        }
                        break;
                }
            } else if (!TextUtils.isEmpty(typeFromItem) &&
                    !"null".equals(typeFromItem.toLowerCase())) {
                Type type = Utils.getType(typeFromItem);
                switch (type) {
                    case FIELD:
                        // Already covered in above verification
                        break;
                    case TEXT:
                        tvContactMessage.setText(item.getMessage());
                        if (item.getHidden().toLowerCase().equals("false")) {
                            tvContactMessage.setVisibility(View.VISIBLE);
                        } else {
                            tvContactMessage.setVisibility(View.INVISIBLE);
                        }
                        break;
                    case IMAGE:
                        // We don't have a rule to handle this case yet
                        break;
                    case CHECKBOX:
                        cbEmail.setText(item.getMessage());
                        cbEmail.setOnCheckedChangeListener(cbListener());
                        break;
                    case SEND:
                        btnSend.setText(item.getMessage());
                        btnSend.setOnClickListener(btnListener());
                        break;
                }
            }
        }

        unhideLayout();
    }

    private CompoundButton.OnCheckedChangeListener cbListener() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    tipEmailField.setVisibility(View.VISIBLE);
                    edtEmailField.setVisibility(View.VISIBLE);
                } else {
                    tipEmailField.setVisibility(View.INVISIBLE);
                    edtEmailField.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    private View.OnClickListener btnListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.validateParams(
                        edtNameField.getText().toString(),
                        edtEmailField.getText().toString(),
                        edtPhoneField.getText().toString()
                );
            }
        };
    }

    private TextWatcher phoneTextWatcher() {
        return new TextWatcher() {
            private boolean isRunning = false;

            // This regex will be used to remove all non-number chars from the given string,
            // so we can properly handle the formatting criteria based on the raw number
            String unmask(String s) {
                return s.replaceAll("[^0-9]*", "");
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

                String unmasked = unmask(editable.toString());

                // This 'isRunning' controller is to prevent from unwanted changes that where not
                // made by the user
                if (isRunning ) {
                    return ;
                }
                isRunning = true;

                int unmaskedLength = unmasked.length();
                int editFieldLength = editable.length();

                if (unmaskedLength == 8) {
                    editable.replace(0,editFieldLength,
                            String.format("%s-%s",              // Format: ####-####
                                    unmasked.substring(0, 4),   // First 4 digits
                                    unmasked.substring(4, 8))); // Last 4 digits
                } else if (unmaskedLength == 9) {
                    // Needs to be 10 (9+1) because the - needs to be count, as the size of unmasked is
                    // smaller rather than editable string (it has the dash char)
                    editable.replace(0,editFieldLength,
                            String.format("%s-%s",              // Format: #####-####
                                    unmasked.substring(0, 5),   // First 5 digits
                                    unmasked.substring(5, 9))); // Last 4 digits
                } else if (unmaskedLength == 10) {
                    editable.replace(0,editFieldLength,
                            String.format("(%s) %s-%s",         // Format: (##) ####-####
                                    unmasked.substring(0, 2),   // The Area Code
                                    unmasked.substring(2, 6),   // The first 4 digits from phone number
                                    unmasked.substring(6, 10)));// The last 4 digits from phone number
                } else if (unmaskedLength == 11) {
                    // Needs to be 15 (12+3) because it has the parenthesis and space char that needs
                    // to be counted in this length
                    editable.replace(0,editFieldLength,
                            String.format("(%s) %s-%s",         // Format: (##) #####-####
                                    unmasked.substring(0, 2),   // The Area Code
                                    unmasked.substring(2, 7),   // The first 5 digits from phone number
                                    unmasked.substring(7, 11)));// The last 4 digits from phone number

                } else {
                    // In case that the number size is smaller than 8 and greather than 11,
                    // no formatting will be applied
                    editable.replace(0, editable.length(),
                            unmasked);
                }

                isRunning = false;
            }
        };

    }

    @Override
    public void paramsValidated(boolean isNameOk, boolean isEmailOk, boolean isPhoneOk) {
        int messageId;
        if (!isNameOk) {
            messageId = R.string.name_not_valid;
        } else if (!isEmailOk) {
            messageId = R.string.email_not_valid;
        } else if (!isPhoneOk) {
            messageId = R.string.phone_not_valid;
        } else {
            messageId = R.string.all_fields_ok;
        }

        Toast.makeText(getActivity(), messageId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLayoutUntilCompletion() {
        tvContactNoInternet.setVisibility(View.INVISIBLE);
        pbContact.setVisibility(View.VISIBLE);
        contactFragmentConstraintLayout.setVisibility(View.INVISIBLE);

    }

    private void unhideLayout() {
        tvContactNoInternet.setVisibility(View.INVISIBLE);
        pbContact.setVisibility(View.INVISIBLE);
        contactFragmentConstraintLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void internetConnectionError() {
        pbContact.setVisibility(View.INVISIBLE);
        contactFragmentConstraintLayout.setVisibility(View.INVISIBLE);
        tvContactNoInternet.setVisibility(View.VISIBLE);
    }
}

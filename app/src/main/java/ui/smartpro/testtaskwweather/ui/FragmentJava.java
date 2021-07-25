package ui.smartpro.testtaskwweather.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.google.android.material.snackbar.Snackbar;
import java.util.Objects;
import kotlin.Lazy;
import ui.smartpro.testtaskwweather.R;
import static org.koin.java.KoinJavaComponent.inject;


public class FragmentJava extends Fragment {

    private final Lazy<WeatherViewModel> weatherViewModel = inject(WeatherViewModel.class);

    private Long zipCodeInput;
    private String zipCodeInputLength;
    private Integer zipCodeInputSize;
    private Boolean flag;

    Button getweatherBtn;
    EditText zipCode;
    TextView locationField;
    TextView temperatureField;
    TextView windField;
    TextView humidityField;
    TextView visibilityField;
    TextView sunriseField;
    TextView sunsetField;

    View viewLaunch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_weather,container,  false);
        viewLaunch  = view;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flag =false;
        getweatherBtn = view.findViewById(R.id.getweatherBtn);
        zipCode = view.findViewById(R.id.zipCodeET);
        locationField = view.findViewById(R.id.locationField);
        temperatureField = view.findViewById(R.id.temperatureField);
        windField = view.findViewById(R.id.windField);
        humidityField = view.findViewById(R.id.humidityField);
        visibilityField = view.findViewById(R.id.visibilityField);
        sunriseField = view.findViewById(R.id.sunriseField);
        sunsetField = view.findViewById(R.id.sunsetField);

        zipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!String.valueOf(zipCode.getText()).equals("")) {
                    zipCodeInput = Long.valueOf(String.valueOf(s));
                    zipCodeInputLength = String.valueOf(s);
                    zipCodeInputSize = zipCodeInputLength.length();
                     weatherViewModel.getValue().validateInput(s.toString());
                }
            }
        });

        getweatherBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!String.valueOf(zipCode.getText()).equals("")) {
                if (zipCodeInputSize >= 5 && zipCodeInputSize <= 6) {
                    weatherViewModel.getValue().updateData(zipCodeInput);
                    weatherViewModel.getValue().getWeatherLive()
                            .observe(getViewLifecycleOwner(), weatherModels -> {
                                flag = false;
                                locationField.setText(weatherModels.getName());
                                temperatureField.setText(weatherModels.getTemperature());
                                windField.setText(weatherModels.getWind());
                                humidityField.setText(weatherModels.getHumidity());
                                visibilityField.setText(weatherModels.getVisibility());
                                sunriseField.setText(weatherModels.getSunrise());
                                sunsetField.setText(weatherModels.getSunset());
                            });

                    weatherViewModel.getValue().getStateBoolean()
                            .observe(getViewLifecycleOwner(), state -> {
                                if (state && flag == true) {
                                    flag = true;
                                    SnackbarShow(v, "Данных нет возможно Вы ввели неверный код или Проверьте наличие интернета!");
                                }
                            });
                } else {
                    SnackbarShow(v, "Zip Code должен быть либо 5-ти знаяным или 6-ти значным числом!");
                }
            } else {
                    SnackbarShow(v, "Введите Zip Code! ");
                }

            }
        });

        Objects.requireNonNull(weatherViewModel.getValue().saveEnabled()).observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                zipCode.setEnabled(true);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        onUpdate(viewLaunch);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewLaunch = null;
    }


    private void  onUpdate(View v) {
        if (!String.valueOf(zipCode.getText()).equals("")) {
            if (zipCodeInputSize >= 5 && zipCodeInputSize <= 6) {
                weatherViewModel.getValue().updateData(zipCodeInput);
                weatherViewModel.getValue().getWeatherLive()
                        .observe(getViewLifecycleOwner(), weatherModels -> {
                            locationField.setText(weatherModels.getName());
                            temperatureField.setText(weatherModels.getTemperature());
                            windField.setText(weatherModels.getWind());
                            humidityField.setText(weatherModels.getHumidity());
                            visibilityField.setText(weatherModels.getVisibility());
                            sunriseField.setText(weatherModels.getSunrise());
                            sunsetField.setText(weatherModels.getSunset());
                        });

                weatherViewModel.getValue().getStateBoolean()
                        .observe(getViewLifecycleOwner(), state -> {
                            if (state) {
                                SnackbarShow(v, "Данных нет возможно Вы ввели неверный код или Проверьте наличие интернета!");
                            }
                        });

            } else {
                SnackbarShow(v, "Zip Code должен быть либо 5-ти знаяным или 6-ти значным числом!");
            }
    }
    }

    private void SnackbarShow(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}

package com.onesoul.moviecataloguefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.onesoul.moviecataloguefinal.notification.DailyReminder;
import com.onesoul.moviecataloguefinal.notification.TodayRelease;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {
    private Switch aSwitchReminder;
    private Switch aSwitchRelease;
    private DailyReminder dailyReminder;
    private TodayRelease todayRelease;
    private Setting setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        aSwitchReminder = findViewById(R.id.sw_daily_reminder);
        aSwitchRelease = findViewById(R.id.sw_today_release);
        Button btnChangeLanguage = findViewById(R.id.btn_change_language);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.setting);
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dailyReminder = new DailyReminder();
        todayRelease = new TodayRelease();
        setting = new Setting(this);

        setaSwitchReminder();
        setaSwitchRelease();

        btnChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
            }
        });

        aSwitchReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitchReminder.isChecked()) {
                    dailyReminder.setDailyAlarm(getApplicationContext());
                    setting.setDailyReminder(true);
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.set_daily_reminder), Toast.LENGTH_SHORT).show();
                } else {
                    dailyReminder.unsetAlarm(getApplicationContext());
                    setting.setDailyReminder(false);
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.unset_daily_reminder), Toast.LENGTH_SHORT).show();
                }
            }
        });

        aSwitchRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitchRelease.isChecked()) {
                    todayRelease.setTodayAlarm(getApplicationContext());
                    setting.setTodayRelease(true);
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.set_today_release), Toast.LENGTH_SHORT).show();
                } else {
                    todayRelease.unsetTodayAlarm(getApplicationContext());
                    setting.setTodayRelease(false);
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.unset_today_release), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setaSwitchReminder() {
        if (setting.getDailyReminder()) {
            aSwitchReminder.setChecked(true);
        } else {
            aSwitchReminder.setChecked(false);
        }
    }

    private void setaSwitchRelease() {
        if (setting.getTodayRelease()) {
            aSwitchRelease.setChecked(true);
        } else {
            aSwitchRelease.setChecked(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.unipu.mobapp.studentplanner;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.charts.CircularGauge;
import com.anychart.core.axes.Circular;
import com.anychart.core.gauge.pointers.Bar;
import com.anychart.enums.Anchor;
import com.anychart.graphics.vector.Fill;
import com.anychart.graphics.vector.SolidFill;
import com.anychart.graphics.vector.text.HAlign;
import com.anychart.graphics.vector.text.VAlign;

public class StatisticActivity extends AppCompatActivity {
    TextView textView;
    AnyChartView anyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        textView = findViewById(R.id.textView);
        anyChartView = findViewById(R.id.anyChart);

        CircularGauge circularGauge = AnyChart.circular();
        circularGauge.data(new SingleValueDataSet(new String[] {"32", "94", "67", "73", "76"}));
        circularGauge.fill("#fff")
                .stroke(null)
                .padding(0d, 0d, 0d, 0d)
                .margin(100d, 100d, 100d, 100d);
        circularGauge.startAngle(0d);
        circularGauge.sweepAngle(270d);

        Circular xAxis = circularGauge.axis(0)
                .radius(100d)
                .width(1d)
                .fill((Fill) null);
        xAxis.scale()
                .minimum(0d)
                .maximum(100d);
        xAxis.ticks("{ interval: 1 }")
                .minorTicks("{ interval: 1 }");
        xAxis.labels().enabled(false);
        xAxis.ticks().enabled(false);
        xAxis.minorTicks().enabled(false);

        //prvi
        circularGauge.label(0d)
                .text("NASP, <span style=\"\">32%</span>")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .vAlign(VAlign.MIDDLE);
        circularGauge.label(0d)
                .anchor(Anchor.RIGHT_CENTER)
                .padding(0d, 10d, 0d, 0d)
                .height(17d / 2d + "%")
                .offsetY(100d + "%")
                .offsetX(0d);
        Bar barFirst = circularGauge.bar(0d);
        barFirst.dataIndex(0d)
                .radius(100d)
                .width(17d)
                .fill(new SolidFill("#64b5f6", 1d))
                .stroke(null)
                .zIndex(5d);
        Bar firstBar = circularGauge.bar(100d);
        firstBar.dataIndex(5d)
                .radius(100d)
                .width(17d)
                .fill(new SolidFill("#F5F4F4", 1d))
                .stroke("1 #e5e4e4")
                .zIndex(4d);
        //drugi
        circularGauge.label(1d)
                .text("Računalna grafika, <span style=\"\">94%</span>")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .vAlign(VAlign.MIDDLE);
        circularGauge.label(1d)
                .anchor(Anchor.RIGHT_CENTER)
                .padding(0d, 10d, 0d, 0d)
                .height(17d / 2d + "%")
                .offsetY(80d + "%")
                .offsetX(0d);
        Bar barSecond = circularGauge.bar(1d);
        barSecond.dataIndex(1d)
                .radius(80d)
                .width(17d)
                .fill(new SolidFill("#1976d2", 1d))
                .stroke(null)
                .zIndex(5d);
        Bar secondBar = circularGauge.bar(101d);
        secondBar.dataIndex(5d)
                .radius(80d)
                .width(17d)
                .fill(new SolidFill("#F5F4F4", 1d))
                .stroke("1 #e5e4e4")
                .zIndex(4d);

        //treci
        circularGauge.label(2d)
                .text("Kriptografija, <span style=\"\">67%</span>")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .vAlign(VAlign.MIDDLE);
        circularGauge.label(2d)
                .anchor(Anchor.RIGHT_CENTER)
                .padding(0d, 10d, 0d, 0d)
                .height(17d / 2d + "%")
                .offsetY(60d + "%")
                .offsetX(0d);
        Bar barThird = circularGauge.bar(2d);
        barThird.dataIndex(2d)
                .radius(60d)
                .width(17d)
                .fill(new SolidFill("#ef6c00", 1d))
                .stroke(null)
                .zIndex(5d);
        Bar thirdBar = circularGauge.bar(102d);
        thirdBar.dataIndex(5d)
                .radius(60d)
                .width(17d)
                .fill(new SolidFill("#F5F4F4", 1d))
                .stroke("1 #e5e4e4")
                .zIndex(4d);

        //četvrti
        circularGauge.label(3d)
                .text("IIP, <span style=\"\">73%</span>")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .vAlign(VAlign.MIDDLE);
        circularGauge.label(3d)
                .anchor(Anchor.RIGHT_CENTER)
                .padding(0d, 10d, 0d, 0d)
                .height(17d / 2d + "%")
                .offsetY(40d + "%")
                .offsetX(0d);
        Bar barFourth = circularGauge.bar(3d);
        barFourth.dataIndex(3d)
                .radius(40d)
                .width(17d)
                .fill(new SolidFill("#ffd54f", 1d))
                .stroke(null)
                .zIndex(5d);
        Bar fourthBar = circularGauge.bar(103d);
        fourthBar.dataIndex(5d)
                .radius(40d)
                .width(17d)
                .fill(new SolidFill("#F5F4F4", 1d))
                .stroke("1 #e5e4e4")
                .zIndex(4d);

        //peti
        circularGauge.label(4d)
                .text("IT menagement, <span style=\"\">76%</span>")
                .useHtml(true)
                .hAlign(HAlign.CENTER)
                .vAlign(VAlign.MIDDLE);
        circularGauge.label(4d)
                .anchor(Anchor.RIGHT_CENTER)
                .padding(0d, 10d, 0d, 0d)
                .height(17d / 2d + "%")
                .offsetY(20d + "%")
                .offsetX(0d);
        Bar barFifth = circularGauge.bar(4d);
        barFifth.dataIndex(4d)
                .radius(20d)
                .width(17d)
                .fill(new SolidFill("#455a64", 1d))
                .stroke(null)
                .zIndex(5d);
        Bar fifthBar = circularGauge.bar(104d);
        fifthBar.dataIndex(5d)
                .radius(20d)
                .width(17d)
                .fill(new SolidFill("#F5F4F4", 1d))
                .stroke("1 #e5e4e4")
                .zIndex(4d);

        //prvi dio
        circularGauge.margin(50d, 50d, 50d, 50d);
        circularGauge.title()
                .text("Prolaznost na ispitnim rokovima' +\n" +
                        "    '<br/><span style=\"color:#554F4F; font-size: 12px;\">(Od 9. mjeseca 2019. do 9. mjeseca 2020.)</span>")
                .useHtml(true);
        circularGauge.title().enabled(true);
        circularGauge.title().hAlign(HAlign.CENTER);
        circularGauge.title()
                .padding(0d, 0d, 0d, 0d)
                .margin(0d, 0d, 20d, 0d);
        anyChartView.setChart(circularGauge);
    }
};

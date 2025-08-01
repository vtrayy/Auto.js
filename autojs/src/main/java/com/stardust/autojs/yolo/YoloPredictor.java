package com.stardust.autojs.yolo;

import android.util.Log;

import com.stardust.autojs.core.opencv.OpenCVHelper;
import com.stardust.autojs.yolo.onnx.domain.DetectResult;

import org.opencv.core.Mat;

import java.util.List;

/**
 * @author TonyJiangWJ
 * @since 2024/6/1
 */
public abstract class YoloPredictor {

    static {
        OpenCVHelper.initIfNeeded(null, () -> {
            Log.d("YoloPredictor", "static initializer: init finished");
        });
    }


    protected boolean init;

    protected List<String> labels;

    protected float confThreshold = 0.35F;
    protected float nmsThreshold = 0.55F;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public float getConfThreshold() {
        return confThreshold;
    }

    public void setConfThreshold(float confThreshold) {
        this.confThreshold = confThreshold;
    }

    public float getNmsThreshold() {
        return nmsThreshold;
    }

    public void setNmsThreshold(float nmsThreshold) {
        this.nmsThreshold = nmsThreshold;
    }

    public boolean isInit() {
        return init;
    }

    public abstract List<DetectResult> predictYolo(Mat image) throws Exception;

    public void release() {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        release();
    }
}

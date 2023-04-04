package com.example.dacs3.model;

import java.util.List;

public class SanPhamMoiModel {
    boolean success;
    String message;
    List<SapPhamMoi> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SapPhamMoi> getResult() {
        return result;
    }

    public void setResult(List<SapPhamMoi> result) {
        this.result = result;
    }
}

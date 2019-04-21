package com.example.lenovo.geek.model;

import com.example.lenovo.geek.bean.Sections;
import com.example.lenovo.geek.net.SectionsCall;

public interface SectionM {
    void getSections(int page,SectionsCall call);
}

package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

import org.junit.Test;
import org.mockito.Mock;

import helper.TestWithMocks;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

public class MainFrameTest extends TestWithMocks {

    @Mock JFrame frame;
    @Mock UiReadyListener uiListener;
    @Mock OnClickListener clickListener;
    private MainFrame mainFrame;

    @Override
    protected void before() {
        mainFrame = new MainFrame(frame, uiListener);
    }

    @Test
    public void init_sets_the_frame_size() {
        mainFrame.initFrame();

        verify(frame).setSize(anyInt(), anyInt());
    }

    @Test
    public void init_sets_the_frame_title() {
        mainFrame.initFrame();

        verify(frame).setTitle(anyString());
    }

    @Test
    public void init_sets_the_frame_location() {
        mainFrame.initFrame();

        verify(frame).setLocation(anyInt(), anyInt());
    }

    @Test
    public void init_sets_the_frame_visible() {
        mainFrame.initFrame();

        verify(frame).setVisible(true);
    }

    @Test
    public void init_sets_the_frame_close() {
        mainFrame.initFrame();

        verify(frame).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

package com.ouchadam.fyp.presentation;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowListener;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainFrameTest extends TestWithMocks {

    @Mock JFrame frame;
    @Mock OnClickListener clickListener;
    private MainFrame mainFrame;

    @Override
    protected void before() {
        mainFrame = new MainFrame(frame);
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
    public void init_sets_the_frame_close_listener() {
        mainFrame.initFrame();

        verify(frame).addWindowListener(any(WindowListener.class));
    }

    @Test
    public void internal_clicks_open_clicks_should_forward_to_listeners() {
        mainFrame.initFrame();
        mainFrame.setOpenMidiListener(clickListener);

        mainFrame.getOpenMidiButton().doClick();

        verify(clickListener).onClick(any(Component.class));
    }

    @Test
    public void internal_clicks_analise_clicks_should_forward_to_listeners() {
        mainFrame.initFrame();
        mainFrame.enableAnalise(true);
        mainFrame.setAnaliseListener(clickListener);

        mainFrame.getAnaliseButton().doClick();

        verify(clickListener).onClick(any(Component.class));
    }
}

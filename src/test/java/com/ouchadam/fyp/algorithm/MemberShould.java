package com.ouchadam.fyp.algorithm;

import helper.TestWithMocks;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemberShould extends TestWithMocks {

    @Mock List<NoteValue> noteValueList;
    @Mock List<NoteType> noteTypesList;

    @Mock Member.Controller controller;

    @Mock Member.Controller.All all;
    @Mock Member.Controller.Only only;

    private Member member;

    @Override
    protected void before() {
        member = new Member(noteValueList, noteTypesList, controller);
        when(controller.all(member)).thenReturn(all);
        when(controller.only(member)).thenReturn(only);
    }

    @Test
    public void delegate_to_all_notetypes() throws Exception {
        member.all().noteTypes();

        verify(controller).all(member);
        verify(all).noteTypes();
    }

    @Test
    public void delegate_to_all_notevalues() throws Exception {
        member.all().noteValues();

        verify(controller).all(member);
        verify(all).noteValues();
    }

    @Test
    public void delegate_to_all_notes() throws Exception {
        member.all().note();

        verify(controller).all(member);
        verify(all).note();
    }
}

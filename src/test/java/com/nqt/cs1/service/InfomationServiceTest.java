package com.nqt.cs1.service;


import com.nqt.cs1.domain.Infomation;
import com.nqt.cs1.repository.InfomationRepository;
import com.nqt.cs1.service.imp.InfomationServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfomationServiceTest {
    @Mock
    InfomationRepository infomationRepository;

    @InjectMocks
    InfomationServiceImp infomationServiceimp;

    @Test
    void testGetAll() {
        Infomation info1 = new Infomation();
        info1.setId(1);
        info1.setReason("Information 1");
        Infomation info2 = new Infomation();
        info2.setId(2);
        info2.setReason("Information 2");
        List<Infomation> mockInformationList = Arrays.asList(info1, info2);
        when(infomationRepository.findAll()).thenReturn(mockInformationList);
        List<Infomation> result = infomationServiceimp.getAll();
        assertEquals(2, result.size(), "Số lượng thông tin không đúng");
        assertEquals("Information 1", result.get(0).getReason(), "Tên của thông tin đầu tiên không đúng");
        assertEquals("Information 2", result.get(1).getReason(), "Tên của thông tin thứ hai không đúng");
        verify(infomationRepository).findAll();
    }

    @Test
    void testGetById() {
        Infomation info = new Infomation();
        info.setId(1);
        info.setReason("Information 1");
        when(infomationRepository.findById(1)).thenReturn(info);
        Infomation result = infomationServiceimp.getById(1);
        assertEquals(1, result.getId(), "ID của thông tin không đúng");
        assertEquals("Information 1", result.getReason(), "Tên của thông tin không đúng");
        verify(infomationRepository).findById(1);
    }

    @Test
    void testSaveInformation() {
        Infomation info = new Infomation();
        info.setId(1);
        info.setReason("Information 1");
        when(infomationRepository.save(info)).thenReturn(info);
        Infomation result = infomationServiceimp.saveInformation(info);
        assertEquals(1, result.getId(), "ID của thông tin không đúng");
        assertEquals("Information 1", result.getReason(), "Tên của thông tin không đúng");
        verify(infomationRepository).save(info);
    }

    @Test
    void testDeleteById() {
        infomationServiceimp.deleteById(1);
        verify(infomationRepository).deleteById(1);
    }

    @Test
    void testFindById() {
        Infomation info = new Infomation();
        info.setId(1);
        info.setReason("Information 1");
        when(infomationRepository.findById(1)).thenReturn(info);
        Infomation result = infomationServiceimp.findById(1);
        assertEquals(1, result.getId(), "ID của thông tin không đúng");
        assertEquals("Information 1", result.getReason(), "Tên của thông tin không đúng");
        verify(infomationRepository).findById(1);
    }

}

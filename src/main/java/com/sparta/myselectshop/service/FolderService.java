package com.sparta.myselectshop.service;


import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.repository.FolderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    @Transactional
    public void addFolder(List<String> folders, User user)
    {
        List<Folder> existingFolders = folderRepository.findAllByUserAndNameIn(user,folders);

        List<Folder> newFolders = new ArrayList<>();

        for(String folderName : folders)
        {
            if(!isExistedFolder(folderName,existingFolders))
            {
                Folder folder = new Folder(folderName,user);
                newFolders.add(folder);
            }else{
                throw new IllegalArgumentException("중복된 폴더명을 제거해주세요! 폴더명: " + folderName);
            }
        }

        folderRepository.saveAll(newFolders);
    }

    private boolean isExistedFolder(String folderName, List<Folder> existingFolders) {
        for(Folder folder : existingFolders) {
            if (folderName.contains(folder.getName())) {
                return true;
            }

        }
        return false;
    }

    // 로그인한 회원이 등록된 모든 폴더 조회
    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user);
        List<FolderResponseDto> responseDtoList = new ArrayList<>();

        for (Folder folder : folderList) {
            responseDtoList.add(new FolderResponseDto(folder));
        }

        return responseDtoList;
    }
}

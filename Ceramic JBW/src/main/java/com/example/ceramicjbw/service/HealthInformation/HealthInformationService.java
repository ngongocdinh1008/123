package com.example.ceramicjbw.service.HealthInformation;

import com.example.ceramicjbw.dto.HealthInformationDTO;
import com.example.ceramicjbw.entity.Category;
import com.example.ceramicjbw.entity.Details;
import com.example.ceramicjbw.entity.HealthInformation;
import com.example.ceramicjbw.repository.CategoryHealthinformationRepository;
import com.example.ceramicjbw.repository.HealthInformationRepository;
import com.example.ceramicjbw.service.Category.CategoryHealthInformation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HealthInformationService implements iHealthInformationService {

    @Autowired
    private HealthInformationRepository healthInformationRepository;

        @Autowired
        private CategoryHealthinformationRepository categoryHealthinformationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HealthInformationDTO createHealthInformation(HealthInformationDTO healthInformationDTO) {
        HealthInformation healthInformation = convertToEntity(healthInformationDTO);
        healthInformation.setInformation(healthInformationDTO.getInformation());
        HealthInformation savedHealthInformation = healthInformationRepository.save(healthInformation);
        return convertToDTO(savedHealthInformation);
    }

    private HealthInformationDTO convertToDTO(HealthInformation healthInformation) {
        return modelMapper.map(healthInformation, HealthInformationDTO.class);
    }

    private HealthInformation convertToEntity(HealthInformationDTO healthInformationDTO) {
        HealthInformation healthInformation = modelMapper.map(healthInformationDTO, HealthInformation.class);
        healthInformation.setDetails(healthInformationDTO.getDetails().stream()
                .map(detailDTO -> new Details(detailDTO.getId(), detailDTO.getTitle(), detailDTO.getInformation(), healthInformation))
                .collect(Collectors.toList()));
        return healthInformation;
    }

    @Override
    public HealthInformationDTO getHealthInformationById(Long id) throws Exception {
        try {
            HealthInformation healthInformation = healthInformationRepository.findById(id)
                    .orElseThrow(() -> new Exception("Không tìm thấy thông Tin sức khỏe với id : " + id));
            return modelMapper.map(healthInformation, HealthInformationDTO.class);
        } catch (Exception e) {
            throw new Exception("Đã xảy ra lỗi khi truy xuất Thông tin sức khỏe theo id: " + id, e);
        }
    }

    @Override
    public List<HealthInformationDTO> getAllHealthInformation() throws Exception {
        try {
            List<HealthInformation> healthInformations = healthInformationRepository.findAll();
            return healthInformations.stream()
                    .map(healthInformation -> modelMapper.map(healthInformation, HealthInformationDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Đã xảy ra lỗi khi truy xuất tất cả Thông tin sức khỏe", e);
        }
    }

    @Override
    public HealthInformationDTO updateHealthInformation(Long healthInformationId, HealthInformationDTO healthInformationDTO) {
        HealthInformation existingHealthInformation = healthInformationRepository.findById(healthInformationId)
                .orElseThrow(() -> new RuntimeException("HealthInformation not found with id: " + healthInformationId));

        // Update thông tin của existingHealthInformation với thông tin từ healthInformationDTO
        existingHealthInformation.setName(healthInformationDTO.getName());
        existingHealthInformation.setInformation(healthInformationDTO.getInformation());

        // Cập nhật Details
        List<Details> updatedDetails = healthInformationDTO.getDetails().stream()
                .map(detailDTO -> new Details(detailDTO.getId(), detailDTO.getTitle(), detailDTO.getInformation(), existingHealthInformation))
                .collect(Collectors.toList());
        existingHealthInformation.setDetails(updatedDetails);

        // Lưu lại thông tin đã cập nhật vào cơ sở dữ liệu
        HealthInformation savedHealthInformation = healthInformationRepository.save(existingHealthInformation);
        return convertToHealthInformationDTO(savedHealthInformation);
    }

    private HealthInformationDTO convertToHealthInformationDTO(HealthInformation healthInformation) {
        return modelMapper.map(healthInformation, HealthInformationDTO.class);
    }

    private HealthInformation convertToHealthInformationEntity(HealthInformationDTO healthInformationDTO) {
        HealthInformation healthInformation = modelMapper.map(healthInformationDTO, HealthInformation.class);
        healthInformation.setDetails(healthInformationDTO.getDetails().stream()
                .map(detailDTO -> new Details(detailDTO.getId(), detailDTO.getTitle(), detailDTO.getInformation(), healthInformation))
                .collect(Collectors.toList()));
        return healthInformation;
    }

    @Override
    public void deleteHealthInformation(Long id) throws Exception {
        try {
            healthInformationRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Đã xảy ra lỗi khi xóa Thông tin sức khỏe có id:" + id, e);
        }
    }
}


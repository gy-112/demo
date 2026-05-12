package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DNIAnalysisService {

    // 충돌 데이터 (나중에 DB로 교체)
    private static final Map<String, Map<String, String[]>> DNI_DATABASE = new HashMap<>();

    static {
        Map<String, String[]> omega3 = new HashMap<>();
        omega3.put("아스피린", new String[]{"HIGH", "지혈 방해 및 내출혈 위험 급증"});
        omega3.put("비타민E", new String[]{"MEDIUM", "혈액 응고 억제 효과 중복"});
        DNI_DATABASE.put("오메가-3", omega3);

        Map<String, String[]> vitaminK = new HashMap<>();
        vitaminK.put("와파린", new String[]{"HIGH", "약효 상실 및 혈전 생성 초래"});
        DNI_DATABASE.put("비타민K", vitaminK);

        Map<String, String[]> ginseng = new HashMap<>();
        ginseng.put("와파린", new String[]{"HIGH", "약효 상실 및 혈전 생성 초래"});
        DNI_DATABASE.put("인삼", ginseng);
    }

    public List<InteractionResult> analyze(List<String> supplements) {
        List<InteractionResult> results = new ArrayList<>();

        for (int i = 0; i < supplements.size(); i++) {
            for (int j = i + 1; j < supplements.size(); j++) {
                String s1 = supplements.get(i);
                String s2 = supplements.get(j);

                if (DNI_DATABASE.containsKey(s1) && DNI_DATABASE.get(s1).containsKey(s2)) {
                    String[] info = DNI_DATABASE.get(s1).get(s2);
                    results.add(new InteractionResult(s1, s2, info[0], info[1]));
                } else if (DNI_DATABASE.containsKey(s2) && DNI_DATABASE.get(s2).containsKey(s1)) {
                    String[] info = DNI_DATABASE.get(s2).get(s1);
                    results.add(new InteractionResult(s2, s1, info[0], info[1]));
                }
            }
        }
        return results;
    }
}
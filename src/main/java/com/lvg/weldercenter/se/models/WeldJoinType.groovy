package com.lvg.weldercenter.se.models

/**
 * Created by Victor on 13.10.2017.
 *  НПАОП 0.00-1.16-96 Правила атестації зварників
 * При атестації слід враховувати тип зварного шва, вид і
 * умови виконання зварного з'єднання:
 * стиковий шов                                  - BW
 * кутовий шов                                   - FW
 * одностороннє зварне з'єднання                 - ss
 * двостороннє зварне з'єднання                  - bs
 * із підкладкою                                 - mb
 * без підкладки                                 - nb
 * із зачищенням кореня шва                      - gg
 * без зачищення кореня шва                      - ng
 * із присадним матеріалом                       - wm
 * без присадного матеріалу                      - nm
 */
enum WeldJoinType implements Serializable{

    BW(code: 'BW', description: 'стыковой шов'),
    FW(code: 'FW', description: 'угловой шов'),
    SS(code: 'ss', description: 'односторонний сварной шов'),
    BS(code: 'bs', description: 'двухсторонний сварной шов'),
    MB(code: 'mb', description: 'с подкладкой'),
    NB(code: 'nb', description: 'без подкладки'),
    GG(code: 'gg', description: 'с зачисткой корня шва'),
    NG(code: 'ng', description: 'без зачистки корня шва'),
    VM(code: 'vm', description: 'с присадочным материалом'),
    NM(code: 'nm', description: 'без присадочного материала')

    String code
    String description

    WeldJoinType(String code, String description){
        this.code = code
        this.description = description
    }

    String toString(){
        return "$code"
    }


}
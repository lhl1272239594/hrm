var fastSlo = [{"value": "1", "text": "急诊"}, {"value": "2", "text": "计划"}, {"value": "3", "text": "备血"}];

/**
 * 用血方式翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function fastSloFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < fastSlo.length; i++) {
        if (fastSlo[i].value == value) {
            return fastSlo[i].text;
        }
    }
}
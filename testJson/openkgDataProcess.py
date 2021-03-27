import json

if __name__=='__main__':
    file_dir = "../火影人物关系.json"
    f = open(file_dir, encoding='utf-8')
    data = json.loads(f.read())
    res = {"nodes": [], "links": []}
    nodes_name = []
    for relation in data:
        if relation["entity_1"] not in nodes_name:
            nodes_name.append(relation["entity_1"])
        if relation["entity_2"] not in nodes_name:
            nodes_name.append(relation["entity_2"])
        res["links"].append({"name": relation["relationship"], "node1": relation["entity_1"], "node2": relation["entity_2"],
                             "type": "not-subclass"})
    for node in nodes_name:
        res["nodes"].append({"name": node})
    fp = open('迭代一_火影人物关系.json', 'w', encoding='utf-8')
    json_array1 = json.dump(res, fp,ensure_ascii=False)
    fp.close()
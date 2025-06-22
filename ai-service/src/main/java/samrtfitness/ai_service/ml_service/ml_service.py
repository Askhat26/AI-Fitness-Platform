import pika
import json

def handle_workout(request):
    goal = request.get("goal", "fitness")
    return {
        "plan": f"Workout plan for {goal}: Pushups, Squats, and Burpees.",
        "note": "Stay consistent and hydrated."
    }

def handle_nutrition(request):
    return {
        "nutritionPlan": f"Nutrition for {request['dietType']} with {request['dailyCalories']} kcal/day",
        "summary": "Balanced macros and sufficient protein intake."
    }

def handle_progress(request):
    weight = request['currentWeight']
    weeks = request['weeks']
    estimated_weight = weight - (0.5 * weeks)
    return {
        "forecast": "Stay on track for best results!",
        "estimatedWeight": estimated_weight
    }

def callback(ch, method, properties, body):
    request_data = json.loads(body)
    routing_key = method.routing_key
    print(f"[x] Received request: {routing_key}")

    if routing_key == "ai.workout.recommend":
        result = handle_workout(request_data)
    elif routing_key == "ai.nutrition.recommend":
        result = handle_nutrition(request_data)
    elif routing_key == "ai.progress.simulate":
        result = handle_progress(request_data)
    else:
        result = {"error": "Unsupported request"}

    ch.basic_publish(
        exchange='',
        routing_key=properties.reply_to,
        properties=pika.BasicProperties(correlation_id=properties.correlation_id),
        body=json.dumps(result)
    )
    ch.basic_ack(delivery_tag=method.delivery_tag)

def main():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    channel.exchange_declare(exchange='ai.exchange', exchange_type='topic')
    result = channel.queue_declare('', exclusive=True)
    queue_name = result.method.queue

    for key in ["ai.workout.recommend", "ai.nutrition.recommend", "ai.progress.simulate"]:
        channel.queue_bind(exchange='ai.exchange', queue=queue_name, routing_key=key)

    channel.basic_consume(queue=queue_name, on_message_callback=callback)
    print("[*] ML service running. Waiting for messages...")
    channel.start_consuming()

if __name__ == "__main__":
    main()
